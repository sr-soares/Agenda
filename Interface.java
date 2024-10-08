import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Font;
import java.lang.String;

public class Interface extends JFrame{
    private Agenda agenda = new Agenda();
    private JTextField entradaName , entradaNumTell;
    private JLabel qtdContato;
    private JList<String> listaContatos;
    private DefaultListModel<String> JlistAtualizada;

    public Interface()
    {
        setTitle("Agenda");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(420, 400);
        setLocationRelativeTo(null);

        JPanel Jpanel = new JPanel();
            Jpanel.setLayout(null);
            Jpanel.setBackground(Color.LIGHT_GRAY);

            JlistAtualizada = new DefaultListModel<>();

            listaContatos = new JList<>(JlistAtualizada);
            listaContatos.setBounds(100,10,5,5);
            listaContatos.setForeground(Color.BLACK);
            listaContatos.setBackground(Color.LIGHT_GRAY);
            listaContatos.setFont(new Font("Arial", Font.BOLD, 14));

            JScrollPane panelList = new JScrollPane(listaContatos);
            panelList.setBounds(153, 178, 205, 130);
            
        ImageIcon imagem = new ImageIcon(getClass().getResource("IMG/BonecoImg.png"));
        JLabel img = new JLabel(imagem);
            img.setBounds(-20, 120, 200, 200);

        JLabel nome = new JLabel("NOME:");
            nome.setBounds(60, 20, 50, 20);
            nome.setForeground(Color.black);
            nome.setFont(new Font("Arial", Font.BOLD, 12));

         qtdContato = new JLabel();
            qtdContato.setBounds(215, 310, 150, 15);
            qtdContato.setForeground(Color.black);
            qtdContato.setFont(new Font("Arial", Font.BOLD, 14));

        JLabel numTel = new JLabel("N° TELEFONE:");
            numTel.setBounds(40, 53, 100, 20);
            numTel.setForeground(Color.black);
            numTel.setFont(new Font("Arial", Font.BOLD, 12));

        JButton btnInserir = new JButton("Inserir");
            btnInserir.setBounds(36, 90, 85, 33);
            btnInserir.setFont(new Font("Arial", Font.BOLD, 12));
            btnInserir.setForeground(Color.black);
            btnInserir.addActionListener(e ->inserir());

        JButton btnBuscar = new JButton("Buscar");
            btnBuscar.setBounds(154, 90, 85, 33);
            btnBuscar.setFont(new Font("Arial", Font.BOLD, 12));
            btnBuscar.setForeground(Color.black);
            btnBuscar.addActionListener(e ->buscar());

        JButton btnRemover = new JButton("Remover");
            btnRemover.setFont(new Font("Arial", Font.BOLD, 12));
            btnRemover.setBounds(270, 90, 85, 33);
            btnRemover.setForeground(Color.black);
            btnRemover.addActionListener(e ->remover());

        JButton btnExibir = new JButton("Exibir Lista");
            btnExibir.setFont(new Font("Arial", Font.BOLD, 12));
            btnExibir.setBounds(205, 150, 100, 28);
            btnExibir.setForeground(Color.black);
            btnExibir.addActionListener(e ->exibirContatos());

        entradaName = new JTextField();
        entradaName.setBounds(154, 18, 200, 28);

        entradaNumTell = new JTextField();
        entradaNumTell.setBounds(154, 51, 200, 28);

        Jpanel.add(nome);
        Jpanel.add(numTel);
        Jpanel.add(btnInserir);
        Jpanel.add(btnBuscar);
        Jpanel.add(btnRemover);
        Jpanel.add(qtdContato);
        Jpanel.add(img);
        Jpanel.add(entradaName);
        Jpanel.add(entradaNumTell);
        Jpanel.add(btnExibir);
        Jpanel.add(panelList);
        add(Jpanel);
        setVisible(true);    
    }
        public void inserir() 
        {
            String nome = entradaName.getText();
            String numCell = entradaNumTell.getText();
                
            if(!nome.isEmpty())
            {
                if(!numCell.isEmpty())
                {
                    if(agenda.inserir(nome, numCell)){
                        JOptionPane.showMessageDialog(null, "Adicionado!!");
                        exibirContatos();
                    }else{
                        JOptionPane.showMessageDialog(null,"Usuário Existente!!");
                    }
                }else{
                    JOptionPane.showMessageDialog(null, "O campo 'N°Telefone' está vazio!");
                }
            }else{
                JOptionPane.showMessageDialog(null, "O campo 'NOME' está vazio!");
            }   
        }
    public void buscar()
    {
        String nome = entradaName.getText();
        if(!nome.isEmpty()){
            if(agenda.buscar(nome) == null){
                JOptionPane.showMessageDialog(null, "Usuário não encontrado!!");
            }else{
                JOptionPane.showMessageDialog(null, "Usuário identificado!!");
            }
        }else{
            JOptionPane.showMessageDialog(null, "O campo 'NOME' está vazio!!");
        }
    }
     public void remover()
     {
        String nome = entradaName.getText();

        if(!nome.isEmpty())
        {
            if(agenda.remover(nome)){
                JOptionPane.showMessageDialog(null, "Usuário Removido");
                exibirContatos();
            }else{
                JOptionPane.showMessageDialog(null, "Usuário não encontrado");
            }
        }else{
        JOptionPane.showMessageDialog(null, "O campo 'NOME' está vazio!");
        }
    }
    public void exibirContatos() {
        JlistAtualizada.clear();
        int qtdContatoInt;

        if (agenda.getQtdContato() > 0)
        {
            qtdContatoInt = agenda.getQtdContato();
            qtdContato.setText(Integer.toString(qtdContatoInt) +" Contatos");

           for(int i=0; i<agenda.getQtdContato(); i++)
            {
                agenda.exibirAgenda(i).getNome();
                String nomeNumero =  agenda.exibirAgenda(i).getNome() + " - " +agenda.exibirAgenda(i).getNumero();
                JlistAtualizada.addElement(nomeNumero);
            }
        } else {
            qtdContato.setText("0 Contatos");
            JOptionPane.showMessageDialog(null, "A lista de contatos está vazia.");
        }
    }
}