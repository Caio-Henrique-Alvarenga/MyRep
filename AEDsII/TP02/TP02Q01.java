import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;


class Filme {
    private String Nome;
    private String TituloOriginal;
    private Date DataDeLancamento;
    private int Duracao;
    private String Genero;
    private String IdiomaOriginal;
    private String Situacao;
    private float Orcamento;
    private String[] PalavrasChave;
    SimpleDateFormat sdp = new SimpleDateFormat("dd/MM/yyyy");

    Filme(String arquivo) {
        this.ler(arquivo);
    }

    public Filme() {

    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String Nome) {
        this.Nome = Nome;
    }

    public String getTituloOriginal() {
        return TituloOriginal;
    }

    public void setTituloOriginal(String TituloOriginal) {
        this.TituloOriginal = TituloOriginal;
    }

    public Date getDataDeLancamento() {
        return DataDeLancamento;
    }

    public void setDataDeLancamento(Date DataDeLancamento) {
        this.DataDeLancamento = DataDeLancamento;
    }

    public int getDuracao() {
        return Duracao;
    }

    public void setDuracao(int Duracao) {
        this.Duracao = Duracao;
    }

    public String getGenero() {
        return Genero;
    }

    public void setGenero(String Genero) {
        this.Genero = Genero;
    }

    public String getIdiomaOriginal() {
        return IdiomaOriginal;
    }

    public void setIdiomaOriginal(String IdiomaOriginal) {
        this.IdiomaOriginal = IdiomaOriginal;
    }

    public String getSituacao() {
        return Situacao;
    }

    public void setSituacao(String Situacao) {
        this.Situacao = Situacao;
    }

    public float getOrcamento() {
        return Orcamento;
    }

    public void setOrcamento(float Orcamento) {
        this.Orcamento = Orcamento;
    }

    public String[] getPalavrasChave() {
        return PalavrasChave;
    }

    public void setPalavrasChave(String[] PalavrasChave) {
        int tamanho = PalavrasChave.length;
        this.PalavrasChave = new String[tamanho];

        for (int i = 0; i < tamanho; i++) {
            this.PalavrasChave[i] = PalavrasChave[i];
        }
    }

    public Filme clone() {
        Filme f = new Filme();
        f.setNome(this.Nome);
        f.setTituloOriginal(this.TituloOriginal);
        f.setDataDeLancamento(this.DataDeLancamento);
        f.setDuracao(this.Duracao);
        f.setGenero(this.Genero);
        f.setIdiomaOriginal(this.IdiomaOriginal);
        f.setSituacao(this.Situacao);
        f.setOrcamento(this.Orcamento);
        f.setPalavrasChave(this.PalavrasChave);
        return f;
    }

    public void ler(String arquivo) {
        int inicio = 0;
        String nome = "";
        Arq.openRead(arquivo, "UTF-8");
        for (int i = 0; i < 3; i++) {
            Arq.readLine();
        }
        String format = Arq.readLine();
        for (int i = 0; i < format.length(); i++) {
            if (format.charAt(i) == '>') {
                inicio = i + 1;
                i = format.length();
            }
        }
        while (inicio < format.length()) {
            if(format.charAt(inicio)=='&'){
                nome+=format.charAt(inicio);
                inicio+=4;
            }
            else{
                nome += format.charAt(inicio);
            }
            
            if (format.charAt(inicio + 1) == ' ' && format.charAt(inicio + 2) == '(') {
                inicio = format.length();
            }
            inicio++;
        }
        this.Nome = nome;
        String data = "";
        boolean flag = false;
        while (!flag && Arq.hasNext()) {
            String linha = Arq.readLine();
            String aux;
            if (linha.contains("<span class=" + "\"" + "release" + "\"")) {
                flag = true;
                aux = Arq.readLine();
                int contador = 0;
                for (int i = 0; i < aux.length(); i++) {
                    if (aux.charAt(i) != ' ' && contador < 10) {
                        contador++;
                        data += aux.charAt(i);
                    }
                }
            }
        }
        try {
            this.DataDeLancamento = sdp.parse(data);
        } catch (Exception e) {
        }
        flag = false;
        String genres = "";
        while (!flag && Arq.hasNext()) {
            String linha = Arq.readLine();
            String aux;
            int pos = 0;
            if (linha.contains("<span class=" + "\"" + "genres" + "\"")) {
                Arq.readLine();
                flag = true;
                aux = Arq.readLine();
                for (int i = 0; i < aux.length(); i++) {
                    if (aux.charAt(i) == '>' && aux.charAt(i - 1) != 'a') {
                        pos = i + 1;
                        while (aux.charAt(pos) != '<') {
                            genres += aux.charAt(pos);
                            pos++;
                        }
                        genres += ',';
                    }
                }
            }
        }
        flag = false;
        String generos = "";
        for (int tam = 0; tam < genres.length() - 1; tam++) {
            generos += genres.charAt(tam);
        }
        this.Genero = generos;
        int runtime = 0;
        while (!flag && Arq.hasNext()) {
            String linha = Arq.readLine();
            String aux;
            if (linha.contains("<span class=" + "\"" + "runtime" + "\"")) {
                Arq.readLine();
                String horas = "";
                String minutos = "";
                flag = true;
                aux = Arq.readLine();
                for (int i = 0; i < aux.length(); i++) {
                    if (aux.charAt(i) == 'h') {
                        horas += aux.charAt(i - 1);
                        runtime += Integer.parseInt(horas);
                        runtime *= 60;
                    }
                    if (aux.charAt(i) == 'm') {
                        if(aux.charAt(i-2)!= ' '){
                            minutos += aux.charAt(i - 2);
                        }
                        minutos += aux.charAt(i - 1);
                        runtime += Integer.parseInt(minutos);
                        
                    }
                }
            }
        }
        flag = false;
        this.Duracao = runtime;
        String tituloO = "";
        while (!flag && Arq.hasNext()) {
            String linha = Arq.readLine();
            int pos = 0;
            if (linha.contains("<p class=" + "\"" + "wrap" + "\"")) {
                flag = true;
                for (int i = 0; i < linha.length(); i++) {
                    if (linha.charAt(i) == '<'&&linha.charAt(i-1)=='l'&&linha.charAt(i-2)=='a') {
                        pos = i + 10;
                    }
                }
                while (linha.charAt(pos) != '<') {
                    tituloO += linha.charAt(pos);
                    pos++;
                }
            }
        }
        if(tituloO.isEmpty()){
            Arq.close();
            Arq.openRead(arquivo, "UTF-8");
            this.TituloOriginal=this.Nome;
        }
        else{
            this.TituloOriginal = tituloO;
        }
        String sit = "";
        flag = false;
        while (!flag && Arq.hasNext()) {
            String linha = Arq.readLine();
            int pos = 0;
            if (linha.contains("<strong><bdi>Situa")) {
                flag = true;
                for (int i = 0; i < linha.length(); i++) {
                    if (linha.charAt(i) == '<' && linha.charAt(i - 1) == 'o') {
                        pos = i + 16;
                    }
                }
                while (pos < linha.length()) {
                    sit += linha.charAt(pos);
                    pos++;
                }
            }
        }
        this.Situacao = sit;
        flag = false;
        String io = "";
        while (!flag && Arq.hasNext()) {
            String linha = Arq.readLine();
            int pos = 0;
            if (linha.contains("Idioma original")) {
                flag = true;
                for (int i = 0; i < linha.length(); i++) {
                    if (linha.charAt(i) == '<' && linha.charAt(i - 1) == 'l') {
                        pos = i + 16;
                    }
                }
                while (linha.charAt(pos) != '<') {
                    io += linha.charAt(pos);
                    pos++;
                }
            }
        }
        this.IdiomaOriginal = io;
        flag = false;
        float orc = 0;
        while (!flag && Arq.hasNext()) {
            String linha = Arq.readLine();
            String dinheiro = "";
            int pos = 0;
            if (linha.contains("<p><strong><bdi>Or")) {
                flag = true;
                for (int i = 0; i < linha.length(); i++) {
                    if (linha.charAt(i) == '<' && linha.charAt(i - 1) == 'o') {
                        pos = i + 17;
                    }
                }
                while (linha.charAt(pos) != '<') {
                    if (linha.charAt(pos) != ','&&linha.charAt(pos)!='-') { 
                        dinheiro += linha.charAt(pos);
                    }
                    pos++;
                }
                if(dinheiro.isEmpty()==false){
                   orc = Float.parseFloat(dinheiro);
                }
                
            }
        }
        this.Orcamento = orc;
        flag = false;
        String[] PC = new String[100];
        while (!flag && Arq.hasNext()) {
            String linha = Arq.readLine();
            String dinheiro = "";
            int pos = 0;
            int contador = 0;
            if (linha.contains("Palavras-chave")) {
                
                Arq.readLine();
                Arq.readLine();
                Arq.readLine();
                flag = true;
                String aux = "";
                while (!aux.contains("</ul>")) {
                    String aux2 = "";
                    aux = Arq.readLine();
                    if (aux.contains("<li>")) {
                        for (int i = 0; i < aux.length(); i++) {
                            if (aux.charAt(i) == '>' && aux.charAt(i - 1) == '"') {
                                pos = i + 1;
                            }
                        }
                        while (aux.charAt(pos) != '<') {
                            aux2 += aux.charAt(pos);
                            pos++;
                        }
                        PC[contador] = aux2;
                        contador++;
                    }
                }
                String[] Palavras = new String[contador];
                for (int i = 0; i < contador; i++) {
                    Palavras[i] = PC[i];
                }
                this.PalavrasChave = Palavras;
            }
        }

        flag = false;
        Arq.close();
    }

    public void Imprimir() {
        System.out.println(this.Nome + " " + this.TituloOriginal + " " + sdp.format(this.DataDeLancamento) + " "
                + this.Duracao
                + " " + this.Genero + " " + this.IdiomaOriginal + " " + this.Situacao + " " + this.Orcamento + " "
                + Arrays.toString(this.PalavrasChave));
    }
}

class TP02Q01 {

    public static boolean isFim(String s) {
        return (s.length() == 3 && s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M');
    }

    public static void main(String[] args) {
        MyIO.setCharset("UTF-8");
        String[] entrada = new String[1000];
        int numEntrada = 0;
        do {
            entrada[numEntrada] = MyIO.readLine();
        } while (isFim(entrada[numEntrada++]) == false);
        numEntrada--; // Desconsiderar ultima linha contendo a palavra FIM
        for (int i = 0; i < numEntrada; i++) {
            String arquivo = "/tmp/filmes/" + entrada[i];
            Filme f = new Filme(arquivo);
            f.Imprimir();
        }
    }
}
