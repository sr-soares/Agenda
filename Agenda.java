import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Agenda {
    private ArrayList<Contato> ListContatos;
    private int posicao;
    
    public Agenda(){  ListContatos = new ArrayList<Contato>(); }

    public boolean inserir(String nome, String telefone) 
    { 
        if(buscar(nome) ==null){
            Contato novoContato = new Contato(nome, telefone);
            ListContatos.add(novoContato);
            return true;
        }
        return false;
    }
    public boolean remover(String nome)
    {
      if(buscar(nome)!=null) {
        ListContatos.remove(posicao);
        return true;
      }
        return false;
    }
    public Contato buscar(String nome)
    {
        for (int i = 0; i < ListContatos.size(); i++) 
        {
            if (ListContatos.get(i).getNome().equalsIgnoreCase(nome)) 
            {
                posicao = i;
                return ListContatos.get(i);
            }
        }
        return null;
    }
    public int getQtdContato()
    {
        return ListContatos.size();
    }
    public Contato exibirAgenda(int i)
    {
        Collections.sort(ListContatos, Comparator.comparing(Contato::getNome));
        return ListContatos.get(i);
    }
}