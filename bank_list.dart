
import 'package:flutter/material.dart';
import 'package:budget_planner/models/user_sql_model.dart';
List<Map<String, dynamic>> _accounts = [];

class BankListWidgets extends StatefulWidget {
  const BankListWidgets({super.key});

  @override
  State<BankListWidgets> createState() => _BankListWidgets();
}

class _BankListWidgets extends State<BankListWidgets> {

  @override
  void initState() {
    super.initState();
    // Corpo do método initState
  }

  void _updateAccountsList() {
    setState(() {
      // Atualiza a lista de contas
    });
  }


  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return DefaultTabController(
      length: 3,
      child: Center(
        child: Scaffold(
          appBar: AppBar(
            backgroundColor: Color(0xff425fff),
            elevation: 0,
              flexibleSpace: TabBar(
                padding: EdgeInsets.symmetric(horizontal: 20, vertical: 2),
                unselectedLabelColor: Colors.white70,
                indicatorSize: TabBarIndicatorSize.tab,
                indicator: BoxDecoration(
                    gradient: LinearGradient(
                        colors: [Colors.lightBlue, Colors.lightBlueAccent]),
                    borderRadius: BorderRadius.circular(50),
                    color: Colors.cyanAccent),
                tabs: [
                  Tab(
                    child: Align(
                      alignment: Alignment.center,
                      child: Text("Contas"),
                    ),
                  ),
                  Tab(
                    child: Align(
                      alignment: Alignment.center,
                      child: Text("Cartões"),
                    ),
                  ),
                  Tab(
                    child: Align(
                      alignment: Alignment.center,
                      child: Text("Transações"),
                    ),
                  ),
                ]),
          ),
          body: Container(
            decoration: BoxDecoration(
                gradient: LinearGradient(
                  colors: [Color(0xff0052c7), Color(0xff425fff)],
                  begin: Alignment.topCenter,
                  end: Alignment.bottomCenter,
                )),
            child: Center(
              child: TabBarView(
                  children: [
                    BankCardList(accounts: _accounts),
                    Tab2Widget(),
                    Tab3Widget(),
                  ]),
            ),
          ),
          floatingActionButton: FloatingActionButton(
            backgroundColor: Colors.indigoAccent,
            foregroundColor: Colors.white,
            onPressed: () {
              showModalBottomSheet(
                context: context,
                builder: (BuildContext context) {
                  return BottomForm(
                      updateAccountsList: _updateAccountsList,
                    update: false,
                    Id: 0,
                  );
                },
              );
            },
            child: const Icon(Icons.add),
          ),
        ),
      ),
    );
  }
}

class BottomForm extends StatefulWidget {
  final void Function() updateAccountsList;
  final bool update;
  final int Id;
  const BottomForm({Key? key, required this.updateAccountsList, required this.update, required this.Id})
      : super(key: key);

  @override
  State<BottomForm> createState() => _BottomFormState();
}

class _BottomFormState extends State<BottomForm> {

  Future<void> _refreshJournals() async {
    final data = await SQLHelper.getAccountItems();
    List<Map<String, dynamic>> updatedData = [];

    updatedData = List.from(data);

    setState(() {
      _accounts = updatedData;
    });
  }

  final _formKey1 = GlobalKey<FormState>();
  final _formKey2 = GlobalKey<FormState>();
  final _formKey3 = GlobalKey<FormState>();





  bool light = true;

  final TextEditingController _bankNameController = TextEditingController();
  final TextEditingController _balanceController = TextEditingController();
  @override
  Widget build(BuildContext context) {
    return DefaultTabController(
      length: 3,
      child: Center(
        child: Scaffold(
          appBar: AppBar(
            backgroundColor: Colors.indigo,
            title: Text("Cadastrar"),
            elevation: 0,
            bottom: TabBar(
                padding: EdgeInsets.symmetric(horizontal: 20, vertical: 2),
                unselectedLabelColor: Colors.white70,
                indicatorSize: TabBarIndicatorSize.tab,
                indicator: BoxDecoration(
                    gradient: LinearGradient(
                        colors: [Colors.lightBlue, Colors.lightBlueAccent]),
                    borderRadius: BorderRadius.circular(50),
                    color: Colors.cyanAccent),
                tabs: [
                  Tab(
                    child: Align(
                      alignment: Alignment.center,
                      child: Text("Contas"),
                    ),
                  ),
                  Tab(
                    child: Align(
                      alignment: Alignment.center,
                      child: Text("Cartões"),
                    ),
                  ),
                  Tab(
                    child: Align(
                      alignment: Alignment.center,
                      child: Text("Transações"),
                    ),
                  ),
                ]),
          ),
          body: TabBarView(
              children: [
                Form(
                  key: _formKey1,
                  child: Column(
                      crossAxisAlignment: CrossAxisAlignment.start,
                      children: <Widget>[
                        Padding(
                          padding:
                          EdgeInsets.symmetric(horizontal: 30, vertical: 8),
                          child: TextField(
                            controller: _bankNameController,
                            decoration: InputDecoration(
                              border: OutlineInputBorder(),
                              hintText: 'Nome do Banco',
                            ),
                          ),
                        ),
                        Padding(
                          padding:
                          EdgeInsets.symmetric(horizontal: 30, vertical: 8),
                          child: TextField(
                            controller: _balanceController,
                            keyboardType: TextInputType.number,
                            decoration: InputDecoration(
                              border: OutlineInputBorder(),
                              hintText: 'Saldo Inicial',
                            ),
                          ),
                        ),
                        Center(
                          child: ElevatedButton(
                            onPressed: () async {
                              if(!widget.update){
                                await _addItem();
                              }else{
                                await _updateItem(widget.Id);
                              }


                              _refreshJournals();

                              Navigator.of(context).pop();
                            },
                            child: const Text('Concluir'),
                          ),

                        ),
                      ]),
                ),
                Form(
                  key: _formKey2,
                  child: Column(
                      crossAxisAlignment: CrossAxisAlignment.start,
                      children: <Widget>[
                        Padding(
                          padding:
                          EdgeInsets.symmetric(horizontal: 30, vertical: 8),
                          child: TextField(
                            decoration: InputDecoration(
                              border: OutlineInputBorder(),
                              hintText: 'Nome do Banco',
                            ),
                          ),
                        ),
                        Padding(
                          padding:
                          EdgeInsets.symmetric(horizontal: 30, vertical: 8),
                          child: TextField(
                            keyboardType: TextInputType.number,
                            decoration: InputDecoration(
                              border: OutlineInputBorder(),
                              hintText: 'Cartão Intenacional',
                            ),
                          ),
                        ),
                        Padding(
                          padding:
                          EdgeInsets.symmetric(horizontal: 30, vertical: 8),
                          child: TextField(
                            keyboardType: TextInputType.number,
                            decoration: InputDecoration(
                              border: OutlineInputBorder(),
                              hintText: 'Tags',
                            ),
                          ),
                        ),
                        Center(
                          child: ElevatedButton(
                            onPressed: () {},
                            child: const Text('Concluir'),
                          ),
                        ),
                      ]),
                ),
                Form(
                  key: _formKey3,
                  child: Column(
                      crossAxisAlignment: CrossAxisAlignment.start,
                      children: <Widget>[
                        Padding(
                          padding:
                          EdgeInsets.symmetric(horizontal: 30, vertical: 8),
                          child: TextField(
                            decoration: InputDecoration(
                              border: OutlineInputBorder(),
                              hintText: 'Valor',
                            ),
                          ),
                        ),
                        Padding(
                          padding:
                          EdgeInsets.symmetric(horizontal: 30, vertical: 8),
                          child: TextField(
                            decoration: InputDecoration(
                              border: OutlineInputBorder(),
                              hintText: 'Método de Pagamento',
                            ),
                          ),
                        ),
                        Padding(
                          padding:
                          EdgeInsets.symmetric(horizontal: 30, vertical: 8),
                          child: TextField(
                            decoration: InputDecoration(
                              border: OutlineInputBorder(),
                              hintText: 'Tags',
                            ),
                          ),
                        ),
                        Center(
                          child: ElevatedButton(
                            onPressed: () {},
                            child: const Text('Concluir'),
                          ),
                        ),
                      ]),
                ),
              ]),
        ),
      ),
    );
  }
  Future<void> _addItem() async {
    await SQLHelper.createAccountItem(
        _bankNameController.text, double.parse(_balanceController.text));
    widget.updateAccountsList(); // Chama a função de atualização da lista
  }

  Future<void> _updateItem(int Id) async {
    await SQLHelper.updateAccountItem(Id,
        _bankNameController.text, double.parse(_balanceController.text));
    final data = await SQLHelper.getAccountItems();
    List<Map<String, dynamic>> updatedData = [];
    updatedData = List.from(data);
    _accounts = updatedData;
  }

  Future<void> _deleteItem(int id) async {
    await SQLHelper.deleteAccountItem(id);
    ScaffoldMessenger.of(context).showSnackBar(const SnackBar(
      content: Text('Successfully deleted an item!'),
    ));
    widget.updateAccountsList(); // Chama a função de atualização da lista
  }
}

class BankCardList extends StatelessWidget {
  final List<Map<String, dynamic>> accounts;

  const BankCardList({Key? key, required this.accounts}) : super(key: key);
  void _updateAccountsList() {
  }
  @override
  Widget build(BuildContext context) {
    return ListView.builder(
      itemCount: accounts.length,
      itemBuilder: (context, index) {
        final account = accounts[index];
        final name = account['bankName'];
        final Saldo = account['balance'];
        final Id = account['id'];


        return Card(
          child: Column(
            mainAxisSize: MainAxisSize.min,
            children: <Widget>[
              ListTile(
                leading: Icon(Icons.album),
                title: Text(name),
                subtitle: Text('Saldo: $Saldo'),
              ),
              Row(
                mainAxisAlignment: MainAxisAlignment.end,
                children: <Widget>[
                  TextButton(
                    child: const Text('Editar'),
                    onPressed: () {
                      showModalBottomSheet(
                        context: context,
                        builder: (BuildContext context) {
                          return BottomForm(
                            updateAccountsList: _updateAccountsList,
                            update: true,
                            Id: Id,
                          );
                        },
                      );
                    },
                  ),
                  const SizedBox(width: 8),
                  TextButton(
                    child: const Icon(Icons.delete),
                    onPressed: () async {

                      await  SQLHelper.deleteAccountItem(Id);
                      final data = await SQLHelper.getAccountItems();
                      List<Map<String, dynamic>> updatedData = [];

                      updatedData = List.from(data);
                      _accounts = updatedData;
                      print("oi");
                    },
                  ),
                  const SizedBox(width: 8),
                ],
              ),
            ],
          ),
        );
      },
    );
  }
}

class Tab2Widget extends StatelessWidget {
  const Tab2Widget({super.key});

  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Center(
        child: SingleChildScrollView(
            child: Center(
          child: Column(
            children: [
              ...List.generate(
                CardBankCards.length,
                    (index) {
                  return Center(
                    child: CardBankCards[index],
                  );
                },
              )
            ],
          ),
        )),
      ),
    );
  }
}

class Tab3Widget extends StatelessWidget {
  const Tab3Widget({super.key});

  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Center(
        child: SingleChildScrollView(
            child: Center(
          child: Column(
            children: [
              ...List.generate(
                transactionCards.length,
                    (index) {
                  return Center(
                    child: transactionCards[index],
                  );
                },
              )
            ],
          ),
        )),
      ),
    );
  }
}

List<Card> BankCards = [
  Card(
    child: Column(
      mainAxisSize: MainAxisSize.min,
      children: <Widget>[
        ListTile(
          leading: Icon(Icons.album),
          title: Text('Banco StackHolder'),
          subtitle: Text('Número da Conta: XXXXXXXXX'),
        ),
        Row(
          mainAxisAlignment: MainAxisAlignment.end,
          children: <Widget>[
            TextButton(
              child: const Text('Editar'),
              onPressed: () {/* ... */},
            ),
            const SizedBox(width: 8),
            TextButton(
              child: const Icon(Icons.delete),
              onPressed: () {/* ... */},
            ),
            const SizedBox(width: 8),
          ],
        ),
      ],
    ),
  ),
];

List<Card> CardBankCards = [
  Card(
    child: Column(
      mainAxisSize: MainAxisSize.min,
      children: <Widget>[
        ListTile(
          leading: Icon(Icons.album),
          title: Text('Cartão XXX - Banco StackHolder'),
          subtitle: Text('Limite: 1700,00'),
        ),
        Row(
          mainAxisAlignment: MainAxisAlignment.end,
          children: <Widget>[
            TextButton(
              child: const Text('Editar'),
              onPressed: () {/* ... */},
            ),
            const SizedBox(width: 8),
            TextButton(
              child: const Icon(Icons.delete),
              onPressed: () {/* ... */},
            ),
            const SizedBox(width: 8),
          ],
        ),
      ],
    ),
  ),


];

List<Card> transactionCards = [
  Card(
    child: Column(
      mainAxisSize: MainAxisSize.min,
      children: <Widget>[
         ListTile(
          leading: Icon(Icons.album),
          title: Text('Transação - Realizada em 17/08/2022'),
          subtitle: Text('Valor: 170,00'),
        ),
        Row(
          mainAxisAlignment: MainAxisAlignment.end,
          children: <Widget>[
            TextButton(
              child: const Text("Editar"),
              onPressed: () {/* ... */},
            ),
            const SizedBox(width: 8),
            TextButton(
              child: const Icon(Icons.delete),
              onPressed: () {/* ... */},
            ),
            const SizedBox(width: 8),
          ],
        ),
      ],
    ),
  ),


];



