import java.io.*;
import java.util;

class Filme{
   private String Nome;
   private String TituloOriginal;
   private Date DataDeLancamento;
   private int Duracao;
   private String Genero;
   private String IdiomaOriginal;
   private String Situacao;
   private float Orcamento;
   private String[] PalavrasChave;

   Filme(){
       public String getNome(){
           return Nome;
       }
       public void setNome(String Nome){
           Nome+=this.Nome;
       }
       public String getTituloOriginal(){
           return TituloOriginal;
       }
       public void setTituloOriginal(String TituloOriginal){
           TituloOriginal=this.TituloOriginal;
       }
       public Date getDataDeLancamento(){
           return DataDeLancamento;
       }
       public void setDataDeLancamento(Date DataDeLancamento){
           DataDeLancamento=this.DataDeLancamento;
       }
       public int getDuracao(){
           return Duracao;
       }
       public void setDuracao(int Duracao){
           Duracao=this.Duracao;
       }
       public String getGenero(){
           return Genero;
       }
       public void setGenero(String Genero){
           Genero+=this.Genero;
       }
       public String getIdiomaOriginal(){
           return IdiomaOriginal;
       }
       public void setIdiomaOriginal(String IdiomaOriginal){
           IdiomaOriginal+=this.IdiomaOriginal;
       }
       public String getSituacao(){
           return Situacao;
       }
       public void setSituacao(String Situacao){
           Situacao+=this.Situacao;
       }
       public float getOrcamento(){
           return OrcOrcamento
       }
       public void setOrcamento(float Orcamento){
           Orcamento=this.Orcamento;
       }
       public String[] getPalavrasChave(){
           return PalavrasChave;
       }
       public void setPalavrasChave(String[] PalavrasChave){
           int tamanho = PalavrasChave.length;
           this.PalavrasChave =  new String[size];

           for(int i = 0; i < tamanho; i++){
               PalavrasChave[i]=this.PalavrasChave[i];
           }
       }
   }
   public Filme clone(){
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
   public void Imprimir(){
       
   }
}