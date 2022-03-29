import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.xml.catalog.Catalog;

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

    public void ler(String arquivo) {
        int inicio=0;
        String nome = "";
        Arq.openRead(arquivo);
        for (int i=0;i<3;i++){
            Arq.readLine();
        }
        String format =  Arq.readLine();
        for(int i=0;i<format.length();i++){
            if(format.charAt(i)=='>'){
                inicio=i+1;
                i = format.length();
            }
        }
        while(inicio < format.length()){
            nome+=format.charAt(inicio);
            if(format.charAt(inicio+1) == ' ' && format.charAt(inicio+2) == '(') {
                inicio = format.length();
            }
            inicio++;
        }
        this.Nome = nome;
        String  data = "";
        SimpleDateFormat sdp = new SimpleDateFormat("dd/MM/yyyy");
        boolean flag=false;
        while(!flag&&Arq.hasNext()){
            String linha=Arq.readLine();
            String aux;
            if(linha.contains("<span class="+"\""+"release"+"\"")){
                flag=true;
                aux=Arq.readLine();
                for(int j=0;j<aux.length();j++){
                    if(aux.charAt(j)!=' '){
                        data+=aux.charAt(j);
                    }
                }
            }
        }
        try{
            this.DataDeLancamento=sdp.parse(data);
        }catch(Exception e){
        }
    }

    Filme(String arquivo) {
        this.ler(arquivo);
    }

    public Filme(){

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

    public void Imprimir() {
        System.out.println(this.Nome + " " + this.TituloOriginal + " " + this.DataDeLancamento + " " + this.Duracao
                + " " + this.Genero + " " + this.IdiomaOriginal + " " + this.Situacao + " " + this.Orcamento + " "
                + Arrays.toString(this.PalavrasChave));
    }
}

class TP02Q01 {
    public static boolean isFim(String s) {
        return (s.length() == 3 && s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M');
    }

    public static void main(String[] args) {
        String arquivo = "filmes/007_ Sem Tempo para Morrer.html";
        Filme f = new Filme(arquivo);
        f.Imprimir();
    }
}