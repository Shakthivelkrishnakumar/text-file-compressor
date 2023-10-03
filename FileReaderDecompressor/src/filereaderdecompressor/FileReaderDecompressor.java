package filereaderdecompressor;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.FileWriter;
import java.io.IOException;
public class FileReaderDecompressor extends JFrame 
{
    private JTextArea textArea;
    private JButton openButton;
    public FileReaderDecompressor() 
    {
        super("Text File Reader");

        textArea = new JTextArea();
        openButton = new JButton("Open File");

        openButton.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                JFileChooser fileChooser = new JFileChooser();
                FileNameExtensionFilter filter = new FileNameExtensionFilter("Text Files", "txt");
                fileChooser.setFileFilter(filter);

                int returnValue = fileChooser.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) 
                {
                    File selectedFile = fileChooser.getSelectedFile();
                    String filePath = selectedFile.getAbsolutePath();
                    try 
                    {
                        BufferedReader reader = new BufferedReader(new FileReader(filePath));
                        StringBuilder stringBuilder = new StringBuilder();
                        String line;
                        while ((line = reader.readLine()) != null) 
                        {
                            stringBuilder.append(line);
                            stringBuilder.append("\n");
                        }
                        reader.close();
                        String loshak = stringBuilder.toString();
                        textArea.setText(loshak);
                        String []chunks=loshak.split(",");
                        //String QUERY;
                        int count=0;
                        String result="";
                        String QUERY;
                        for(String x:chunks)
                        {
                            if(x.equals("CaRaXeS"))
                                continue;
                            else
                            {
                                 System.out.println(x);
                                QUERY= "SELECT * FROM dict"; 
                                try(Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "javaproject", "loshak");
                                Statement stmt = conn.createStatement();) 
                                {
                                    ResultSet rs = stmt.executeQuery(QUERY);
                                    while(rs.next())
                                    {
                                        if(x.equals(rs.getString("subscript")))
                                            result=result+rs.getString("data")+" ";
                                    }
                                }
                                catch (SQLException k) 
                                {
                                    k.printStackTrace();
                                } 
                            }
                        }
                        System.out.print("Result :\n"+result);
                        FileWriter fWriter = new FileWriter(filePath);
                        fWriter.write(result);
                        fWriter.close();
                    } 
                    catch (Exception ex) 
                    {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(null, "Error reading file", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(openButton, BorderLayout.NORTH);
        panel.add(new JScrollPane(textArea), BorderLayout.CENTER);

        add(panel);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);
        setVisible(true);
    }
    private static String convertToLoshak(int decimal) {
        StringBuilder hexadecimal = new StringBuilder();
        
        while (decimal > 0) {
            int remainder = decimal % 62;
            if (remainder < 10) {
                hexadecimal.insert(0, (char) (remainder + 48)); // ASCII value of '0' is 48
            } else if (remainder<36){
                hexadecimal.insert(0, (char) (remainder + 55)); // ASCII values of 'A' to 'F' are 65 to 70
            }
            else
            {
               hexadecimal.insert(0, (char) (remainder + 61)); 
            }
            decimal /= 62;
        }

        return hexadecimal.toString();
    }
    public static void main(String[] args) 
    {
        FileReaderDecompressor reader = new FileReaderDecompressor();
    }
}