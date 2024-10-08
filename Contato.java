public class Contato {
    private String nome, numero;

    public Contato(String nome, String telefone)
    {
        this.nome = nome;   this.numero = telefone;
    }
    
    public String getNome(){ return nome; }
   
    public String getNumero(){ return numero; }

}