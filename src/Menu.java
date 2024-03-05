import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.*;

public class Menu extends JFrame {

    static ArrayList<String> server = Database.availableservers();

    static Color black = new Color(0, 0, 0);
    static Color almostblack = new Color(192, 192, 192);
    static Color lightgray = new Color(160, 160, 160);
    static Color gray = new Color(128, 128, 128);
    static Color white = new Color(255, 255, 255);

    public static void ShowMenu() throws IOException {

        File input_file = new File(
                "D:/tadaaa/java/Growing in LightCity/src/background4.jpg");

        BufferedImage myImage = ImageIO.read(input_file);

        JFrame frame = new JFrame("<Light City>");
        ImagePanel imagePanel = new ImagePanel(myImage);
        frame.setContentPane(imagePanel);
        Font f1 = new Font(Font.SERIF, Font.BOLD, 19);
        Font f2 = new Font(Font.SERIF, Font.BOLD, 23);

        JLabel l1 = new JLabel("Welcome To LightCity");
        l1.setBounds(130, 35, 250, 40);
        l1.setFont(f2);
        l1.setForeground(gray);

        JButton b1 = new JButton("Continue Your Game");
        b1.setBounds(0, 140, 230, 40);
        b1.setFont(f1);
        b1.setForeground(almostblack);
        b1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    Continue();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                frame.dispose();
            }
        });

        JButton b2 = new JButton("Start New Game");
        b2.setBounds(0, 200, 200, 40);
        b2.setFont(f1);
        b2.setForeground(almostblack);
        b2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    StartNewGame();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                frame.dispose();
            }
        });

        JButton b3 = new JButton("Make Server");
        b3.setBounds(0, 270, 170, 40);
        b3.setFont(f1);
        b3.setForeground(almostblack);
        b3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    MakeServer();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                frame.dispose();
            }
        });

        JButton b4 = new JButton("Exit");
        b4.setBounds(0, 340, 100, 40);
        b4.setFont(f1);
        b4.setForeground(almostblack);
        b4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                frame.dispose();
            }
        });

        b1.setBorderPainted(false);
        b1.setFocusPainted(false);
        b1.setContentAreaFilled(false);
        b2.setBorderPainted(false);
        b2.setFocusPainted(false);
        b2.setContentAreaFilled(false);
        b3.setBorderPainted(false);
        b3.setFocusPainted(false);
        b3.setContentAreaFilled(false);
        b4.setBorderPainted(false);
        b4.setFocusPainted(false);
        b4.setContentAreaFilled(false);

        frame.add(l1);
        frame.add(b1);
        frame.add(b2);
        frame.add(b3);
        frame.add(b4);
        frame.setSize(500, 500);
        frame.setLayout(null);
        frame.setVisible(true);
    }

    public static void Continue() throws IOException {
        File input_file = new File(
                "D:/tadaaa/java/Growing in LightCity/src/background4.jpg");

        BufferedImage myImage = ImageIO.read(input_file);

        JFrame frame = new JFrame("<Light City>");
        ImagePanel imagePanel = new ImagePanel(myImage);
        frame.setContentPane(imagePanel);
        final JLabel label = new JLabel();
        final JPasswordField value = new JPasswordField();
        final JTextField text = new JTextField();
        label.setBounds(125, 55, 130, 35);
        value.setBounds(160, 105, 130, 30);
        text.setBounds(160, 55, 130, 30);

        frame.getContentPane().setBackground(almostblack);

        Font f1 = new Font(Font.SERIF, Font.BOLD, 16);
        Font f2 = new Font(Font.SERIF, Font.BOLD, 20);

        JLabel l1 = new JLabel(" UserName :   ");
        l1.setBounds(55, 50, 150, 40);
        l1.setFont(f1);
        l1.setForeground(lightgray);

        JLabel l2 = new JLabel(" Password :  ");
        l2.setBounds(55, 100, 150, 40);
        l2.setFont(f1);
        l2.setForeground(lightgray);

        JLabel l = new JLabel("  Server   ");
        l.setBounds(65, 170, 150, 40);
        l.setFont(f1);
        l.setForeground(lightgray);
        String[] servers = new String[server.size()];
        for (int i = 0; i < server.size(); i++) {
            servers[i] = server.get(i);
        }
        final JComboBox<String> cb = new JComboBox<>(servers);
        cb.setBounds(160, 180, 130, 25);

        JButton b = new JButton("  Login  ");
        b.setBounds(140, 240, 150, 40);
        b.setFont(f2);
        b.setForeground(lightgray);
        b.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                try {
                    if (Database.login(text.getText(), value.getText(), cb.getSelectedItem().toString())) {

                        try {
                            AvatarMenu(Database.findAvatar(text.getText(), cb.getSelectedItem().toString()));
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                        frame.dispose();
                        return;
                    }
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }

                int x = JOptionPane.showConfirmDialog(frame,
                        "Wrong UserName, Password Or Server \n Do You Want To Try Again?",
                        " ",
                        JOptionPane.YES_NO_OPTION);
                if (x == JOptionPane.YES_OPTION) {
                    try {
                        Continue();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    frame.dispose();

                } else {
                    try {
                        ShowMenu();
                        frame.dispose();

                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }

                }
            }
        });

        JButton back = new JButton("<<<<");
        back.setBounds(0, 420, 90, 30);
        back.setFont(f1);
        back.setForeground(lightgray);
        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    ShowMenu();
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                frame.dispose();
            }
        });

        b.setBorderPainted(false);
        b.setFocusPainted(false);
        b.setContentAreaFilled(false);
        back.setBorderPainted(false);
        back.setFocusPainted(false);
        back.setContentAreaFilled(false);
        frame.add(back);

        frame.add(value);
        frame.add(l1);
        frame.add(l2);
        frame.add(l);
        frame.add(cb);
        frame.add(label);
        frame.add(b);
        frame.add(text);
        frame.setSize(500, 500);
        frame.setLayout(null);
        frame.setVisible(true);

    }

    public static void StartNewGame() throws IOException {

        File input_file = new File(
                "D:/tadaaa/java/Growing in LightCity/src/sabtahval.jpg");

        BufferedImage myImage = ImageIO.read(input_file);

        JFrame frame = new JFrame("<Light City>");
        ImagePanel imagePanel = new ImagePanel(myImage);
        frame.setContentPane(imagePanel);
        final JLabel label = new JLabel();
        final JPasswordField value1 = new JPasswordField();
        final JPasswordField value2 = new JPasswordField();
        final JTextField text = new JTextField();
        label.setBounds(125, 55, 130, 35);
        value1.setBounds(185, 105, 130, 30);
        value2.setBounds(185, 155, 130, 30);
        text.setBounds(185, 55, 130, 30);

        frame.getContentPane().setBackground(almostblack);

        Font f1 = new Font(Font.SERIF, Font.BOLD, 16);
        Font f2 = new Font(Font.SERIF, Font.BOLD, 20);

        JLabel l1 = new JLabel(" UserName :   ");
        l1.setBounds(40, 50, 150, 40);
        l1.setFont(f1);
        l1.setForeground(black);
        // At least six digits is recommended
        JLabel l2 = new JLabel(" Password :  ");
        l2.setBounds(40, 100, 150, 40);
        l2.setFont(f1);
        l2.setForeground(black);

        JLabel l3 = new JLabel("Repeat Password :  ");
        l3.setBounds(45, 150, 150, 40);
        l3.setFont(f1);
        l3.setForeground(black);

        JLabel l = new JLabel("Choose Server   ");
        l.setBounds(45, 200, 150, 40);
        l.setFont(f1);
        l.setForeground(black);
        String[] servers = new String[server.size()];
        for (int i = 0; i < server.size(); i++) {
            servers[i] = server.get(i);
        }

        final JComboBox<String> cb = new JComboBox<>(servers);
        cb.setBounds(185, 208, 130, 25);

        JButton b = new JButton("  Sign Up  ");
        b.setBounds(150, 280, 150, 40);
        b.setFont(f2);
        b.setForeground(black);
        b.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                if (value1.getText().equals(value2.getText())) {
                    try {
                        if (Database.AddAvatar(text.getText(), value1.getText(), cb.getSelectedItem().toString())) {
                            Avatar avatar = new Avatar(text.getText(), "None", 1500, 10, 10, 10,
                                    cb.getSelectedItem().toString(),"None");
                            try {
                                AvatarMenu(avatar);
                            } catch (IOException ex) {
                                throw new RuntimeException(ex);
                            }
                            frame.dispose();
                        } else {
                            int x = JOptionPane.showConfirmDialog(frame,
                                    "This Username Already Exists\n Do You Want To Try Again?",
                                    " ",
                                    JOptionPane.YES_NO_OPTION);
                            if (x == JOptionPane.YES_OPTION) {
                                try {
                                    StartNewGame();
                                } catch (IOException ex) {
                                    throw new RuntimeException(ex);
                                }
                                frame.dispose();
                            } else {
                                try {
                                    ShowMenu();
                                } catch (IOException e1) {
                                    // TODO Auto-generated catch block
                                    e1.printStackTrace();
                                }
                                frame.dispose();

                            }

                        }
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }

                } else {
                    int x = JOptionPane.showConfirmDialog(frame,
                            "Entered Different Passwords\n Do You Want To Try Again?",
                            " ",
                            JOptionPane.YES_NO_OPTION);
                    if (x == JOptionPane.YES_OPTION) {
                        try {
                            StartNewGame();
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                        frame.dispose();
                    } else {
                        try {
                            ShowMenu();
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                        frame.dispose();

                    }
                }

            }
        });

        JButton back = new JButton("<<<<");
        back.setBounds(0, 420, 90, 30);
        back.setFont(f1);
        back.setForeground(black);
        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    ShowMenu();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                frame.dispose();
            }
        });

        b.setBorderPainted(false);
        b.setFocusPainted(false);
        b.setContentAreaFilled(false);
        back.setBorderPainted(false);
        back.setFocusPainted(false);
        back.setContentAreaFilled(false);

        frame.add(back);

        frame.add(value1);
        frame.add(value2);
        frame.add(l1);
        frame.add(l2);
        frame.add(l3);
        frame.add(l);
        frame.add(cb);
        frame.add(label);
        frame.add(b);
        frame.add(text);
        frame.setSize(500, 500);
        frame.setLayout(null);
        frame.setVisible(true);

    }

    public static void MakeServer() throws IOException {

        File input_file = new File(
                "D:/tadaaa/java/Growing in LightCity/src/makeserver.jpg");

        BufferedImage myImage = ImageIO.read(input_file);

        JFrame frame = new JFrame("<Light City>");
        ImagePanel imagePanel = new ImagePanel(myImage);
        frame.setContentPane(imagePanel);
        final JLabel label = new JLabel();
        final JPasswordField value1 = new JPasswordField();
        final JPasswordField value2 = new JPasswordField();

        final JTextField text1 = new JTextField();
        final JTextField text2 = new JTextField();

        label.setBounds(125, 55, 130, 35);
        value1.setBounds(185, 95, 130, 30);
        value2.setBounds(185, 145, 130, 30);
        text1.setBounds(185, 45, 130, 30);
        text2.setBounds(185, 215, 130, 30);

        Font f1 = new Font(Font.SERIF, Font.BOLD, 16);
        Font f2 = new Font(Font.SERIF, Font.BOLD, 20);

        JLabel l1 = new JLabel(" UserName :   ");
        l1.setBounds(40, 40, 150, 40);
        l1.setFont(f1);
        l1.setForeground(black);
        // At least six digits is recommended
        JLabel l2 = new JLabel(" Password :  ");
        l2.setBounds(40, 90, 150, 40);
        l2.setFont(f1);
        l2.setForeground(black);

        JLabel l3 = new JLabel("Repeat Password :  ");
        l3.setBounds(45, 140, 150, 40);
        l3.setFont(f1);
        l3.setForeground(black);

        JLabel l4 = new JLabel("Server Name : ");
        l4.setBounds(45, 210, 150, 40);
        l4.setFont(f1);
        l4.setForeground(black);

        JButton b = new JButton("  Sign Up  ");
        b.setBounds(150, 280, 150, 40);
        b.setFont(f2);
        b.setForeground(white);
        b.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                if (value1.getText().equals(value2.getText())) {
                    try {
                        if (Database.AddServer(text1.getText(), value1.getText(), text2.getText())) {
                            Avatar avatar = new Avatar(text1.getText(), "Mayor", 1500, 10, 10, 10, text2.getText(),"Town Hall");
                            for(int x=15;x<=375; x=x+90) {
                                for (int y = 15; y <= 375; y = y + 90) {
                                    if ((x + 90) == y || (y + 90) == x || (x + 270) == y || (y + 270) == x || (x == 195 && y == 195)) {
                                        continue;
                                    }
                                    Property property = new Property("Field", text2.getText(),text1.getText(),"None",0,0,0,x,y,"Not Sold","No",0);
                                    avatar.AddProperties(property);
                                    Database.AddBuilding(text1.getText(), text2.getText(),x,y);
                                }


                            }

                            try {
                                AvatarMenu(avatar);
                            } catch (IOException ex) {
                                throw new RuntimeException(ex);
                            }

                            frame.dispose();
                        } else {
                            int x = JOptionPane.showConfirmDialog(frame,
                                    "This Server Name Already Exists\n Do You Want To Try Again?",
                                    " ",
                                    JOptionPane.YES_NO_OPTION);
                            if (x == JOptionPane.YES_OPTION) {
                                try {
                                    MakeServer();
                                } catch (IOException ex) {
                                    throw new RuntimeException(ex);
                                }
                                frame.dispose();
                            } else {
                                try {
                                    ShowMenu();
                                } catch (IOException e1) {
                                    e1.printStackTrace();
                                }
                                frame.dispose();

                            }
                        }
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }

                } else {
                    int x = JOptionPane.showConfirmDialog(frame,
                            "Entered Different Passwords\n Do You Want To Try Again?",
                            " ",
                            JOptionPane.YES_NO_OPTION);
                    if (x == JOptionPane.YES_OPTION) {
                        try {
                            MakeServer();
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                        frame.dispose();
                    } else {
                        try {
                            ShowMenu();
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                        frame.dispose();

                    }
                }

            }
        });

        JButton back = new JButton("<<<<");
        back.setBounds(0, 420, 90, 30);
        back.setFont(f1);
        back.setForeground(white);
        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    ShowMenu();
                } catch (IOException e1) {

                      e1.printStackTrace();
                }
                frame.dispose();
            }
        });

        b.setBorderPainted(false);
        b.setFocusPainted(false);
        b.setContentAreaFilled(false);
        back.setBorderPainted(false);
        back.setFocusPainted(false);
        back.setContentAreaFilled(false);

        frame.add(back);

        frame.add(value1);
        frame.add(value2);
        frame.add(l1);
        frame.add(l2);
        frame.add(l3);
        frame.add(l4);
        frame.add(label);
        frame.add(b);
        frame.add(text1);
        frame.add(text2);

        frame.setSize(500, 500);
        frame.setLayout(null);
        frame.setVisible(true);

    }

    public static void AvatarMenu(Avatar avatar) throws IOException {

        File input_file = new File(
                "D:/tadaaa/java/Growing in LightCity/src/a.jpg");

        BufferedImage myImage = ImageIO.read(input_file);

        JFrame frame = new JFrame("<"+avatar.getCity()+">");
        ImagePanel imagePanel = new ImagePanel(myImage);
        frame.setContentPane(imagePanel);
        Font f1 = new Font(Font.SERIF, Font.BOLD, 18);

        JButton b1 = new JButton("Go To");
        b1.setBounds(0, 115, 120, 40);
        b1.setFont(f1);
        b1.setForeground(lightgray);
        b1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    avatar.GoTo();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                frame.dispose();
            }
        });

        JButton b3 = new JButton("Dashbord");
        b3.setBounds(0, 165, 150, 40);
        b3.setFont(f1);
        b3.setForeground(lightgray);
        b3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    Dashboard(avatar);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                frame.dispose();
            }
        });

        JButton b4 = new JButton("Life");
        b4.setBounds(0, 215, 110, 40);
        b4.setFont(f1);
        b4.setForeground(lightgray);
        b4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    avatar.Life();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                frame.dispose();
            }
        });

        JButton b5 = new JButton("Exit");
        b5.setBounds(5, 265, 100, 40);
        b5.setFont(f1);
        b5.setForeground(lightgray);
        b5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });

        JButton back = new JButton("<<<<");
        back.setBounds(0, 420, 90, 30);
        back.setFont(f1);
        back.setForeground(lightgray);
        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    Continue();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                frame.dispose();
            }
        });

        b1.setBorderPainted(false);
        b1.setFocusPainted(false);
        b1.setContentAreaFilled(false);
        b3.setBorderPainted(false);
        b3.setFocusPainted(false);
        b3.setContentAreaFilled(false);
        b4.setBorderPainted(false);
        b4.setFocusPainted(false);
        b4.setContentAreaFilled(false);
        b5.setBorderPainted(false);
        b5.setFocusPainted(false);
        b5.setContentAreaFilled(false);
        back.setBorderPainted(false);
        back.setFocusPainted(false);
        back.setContentAreaFilled(false);

        frame.add(b1);
        frame.add(b3);
        frame.add(b4);
        frame.add(b5);
        frame.add(back);
        frame.setSize(500, 500);
        frame.setLayout(null);
        frame.setVisible(true);

    }

    public static void Dashboard(Avatar avatar) throws IOException {

        File input_file = new File(
                "D:/tadaaa/java/Growing in LightCity/src/dashboard.jpg");

        BufferedImage myImage = ImageIO.read(input_file);

        JFrame frame = new JFrame("<"+avatar.getCity()+">");
        ImagePanel imagePanel = new ImagePanel(myImage);
        frame.setContentPane(imagePanel);

        Font f1 = new Font(Font.SERIF, Font.BOLD, 18);
        Font f2 = new Font(Font.SERIF, Font.BOLD, 20);

        JLabel l1 = new JLabel("Dashboard");
        l1.setBounds(175, 35, 150, 40);
        l1.setFont(f2);
        l1.setForeground(lightgray);

        JButton b1 = new JButton("My Job");
        b1.setBounds(5, 100, 120, 40);
        b1.setFont(f1);
        b1.setForeground(white);
        b1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    avatar.Myjob();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                frame.dispose();
            }
        });

        JButton b2 = new JButton("Properties");
        b2.setBounds(5, 150, 140, 40);
        b2.setFont(f1);
        b2.setForeground(white);
        b2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    avatar.Properties();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                frame.dispose();
            }
        });

        JButton b3 = new JButton("Economy");
        b3.setBounds(5, 200, 130, 40);
        b3.setFont(f1);
        b3.setForeground(white);
        b3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    avatar.Economy();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                frame.dispose();
            }
        });

        JButton b4 = new JButton("Exit");
        b4.setBounds(10, 250, 80, 40);
        b4.setFont(f1);
        b4.setForeground(white);
        b4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });

        JButton back = new JButton("<<<<");
        back.setBounds(0, 420, 90, 30);
        back.setFont(f1);
        back.setForeground(lightgray);
        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    AvatarMenu(avatar);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                frame.dispose();
            }
        });

        b1.setBorderPainted(false);
        b1.setFocusPainted(false);
        b1.setContentAreaFilled(false);
        b2.setBorderPainted(false);
        b2.setFocusPainted(false);
        b2.setContentAreaFilled(false);
        b3.setBorderPainted(false);
        b3.setFocusPainted(false);
        b3.setContentAreaFilled(false);
        b4.setBorderPainted(false);
        b4.setFocusPainted(false);
        b4.setContentAreaFilled(false);
        back.setBorderPainted(false);
        back.setFocusPainted(false);
        back.setContentAreaFilled(false);


        frame.add(l1);
        frame.add(b1);
        frame.add(b2);
        frame.add(b3);
        frame.add(b4);
        frame.add(back);
        frame.setSize(500, 500);
        frame.setLayout(null);
        frame.setVisible(true);

    }


}
