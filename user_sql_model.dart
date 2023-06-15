import 'package:flutter/foundation.dart';
import 'package:sqflite/sqflite.dart' as sql;

class SQLHelper {
  static Future<void> createTables(sql.Database database) async {
    await database.execute("""CREATE TABLE items(
        id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
        nome TEXT,
        email TEXT,
        senha TEXT,
        createdAt TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
      )
      """);

    await database.execute("""
  CREATE TABLE accounts(
    id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    bankName TEXT,
    balance REAL,
    userId INTEGER,
    FOREIGN KEY (userId) REFERENCES users(id) ON DELETE CASCADE
  )
""");

    await database.execute("""
      CREATE TRIGGER delete_accounts
      AFTER DELETE ON items
      FOR EACH ROW
      BEGIN
        DELETE FROM accounts WHERE userId = OLD.id;
      END
    """);
  }
// id: the id of a item
// title, description: name and description of your activity
// created_at: the time that the item was created. It will be automatically handled by SQLite

  static Future<sql.Database> db() async {
    return sql.openDatabase(
      'dbtech4.db',
      version: 1,
      onCreate: (sql.Database database, int version) async {
        await createTables(database);
      },
    );
  }

  // Create new item (journal)
  static Future<int> createItem(String nome, String? email, String? senha) async {
    final db = await SQLHelper.db();

    final data = {'nome': nome, 'email': email, 'senha': senha};
    final id = await db.insert('items', data,
        conflictAlgorithm: sql.ConflictAlgorithm.replace);
    return id;
  }

  static Future<int> createAccountItem(int userId, String bankName, double balance) async {
    final db = await SQLHelper.db();

    final data = {'userId': userId, 'bankName': bankName, 'balance': balance};
    final id = await db.insert('accounts', data,
        conflictAlgorithm: sql.ConflictAlgorithm.replace);
    print(data);
    return id;
  }

  // Read all items (journals)
  static Future<List<Map<String, dynamic>>> getItems() async {
    final db = await SQLHelper.db();
    return db.query('items', orderBy: "id");
  }

  static Future<int> getUserId(String email) async {
    final db = await SQLHelper.db();
    final result = await db.query('items', where: "email = ?", whereArgs: [email], limit: 1);
    final row = result.first;
    String Id = row['id'].toString();
    return int.parse(Id);
  }

  static Future<List<Map<String, dynamic>>> getAccountItems() async {
    final db = await SQLHelper.db();
    return db.query('accounts', orderBy: "id");
  }

  // Read a single item by id
  // The app doesn't use this method but I put here in case you want to see it
  static Future<List<Map<String, dynamic>>> getItem(int id) async {
    final db = await SQLHelper.db();
    return db.query('items', where: "id = ?", whereArgs: [id], limit: 1);
  }

  static Future<Map<String, String>> getItemByEmail(String email) async {
    final db = await SQLHelper.db();
    final result = await db.query('items', where: "email = ?", whereArgs: [email], limit: 1);

    if (result.isNotEmpty) {
      final user = <String, String>{};
      final row = result.first;

      user['id'] = row['id'].toString();
      user['nome'] = row['nome'].toString();
      user['email'] = row['email'].toString();
      user['senha'] = row['senha'].toString();

      print('coisa' + user.toString());
      return user;
    }

    return {};
  }

  static Future<bool> getItemByEmailBool(String email, String senha) async {
    bool existe = false;
    final db = await SQLHelper.db();
    final result = await db.query('items', where: "email = ? and senha= ?", whereArgs: [email, senha], limit: 1);

    if (result.isNotEmpty) {
      final user = <String, String>{};
      final row = result.first;

      user['id'] = row['id'].toString();
      user['nome'] = row['nome'].toString();
      user['email'] = row['email'].toString();
      user['senha'] = row['senha'].toString();

      print('coisa' + user.toString());
      existe = true;
      return existe;
    }

    return existe;
  }


  // Update an item by id
  static Future<int> updateItem(
      int id, String nome, String? email, String? senha) async {
    final db = await SQLHelper.db();

    final data = {
      'nome': nome,
      'email': email,
      'senha': senha,
      'createdAt': DateTime.now().toString()
    };

    final result =
    await db.update('items', data, where: "id = ?", whereArgs: [id]);
    return result;
  }

  static Future<int> updateAccountItem(
      int id, String nome, double saldo) async {
    final db = await SQLHelper.db();

    final data = {
      'bankName': nome,
      'balance': saldo,
    };

    final result =
    await db.update('accounts', data, where: "id = ?", whereArgs: [id]);
    return result;
  }

  // Delete
  static Future<void> deleteItem(int id) async {
    final db = await SQLHelper.db();
    try {
      await db.delete("items", where: "id = ?", whereArgs: [id]);
    } catch (err) {
      debugPrint("Something went wrong when deleting an item: $err");
    }
  }

  static Future<void> deleteAccountItem(int id) async {
    final db = await SQLHelper.db();
    try {
      await db.delete("accounts", where: "id = ?", whereArgs: [id]);
    } catch (err) {
      debugPrint("Something went wrong when deleting an item: $err");
    }
  }
}