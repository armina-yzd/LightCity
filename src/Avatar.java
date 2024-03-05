import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.*;

public class Avatar {
    private String username;
    private String title;
    private float money;
    private float food;
    private float sleep;
    private float water;
    private final String city;
    private String workPlace;

    private ArrayList<Property> properties = new ArrayList<>();

    public Avatar(String username, String title, float Money, float food, float sleep, float water, String City, String work) {
        this.username = username;
        this.title = title;
        this.money = Money;
        this.food = food;
        this.sleep = sleep;
        this.water = water;
        this.city = City;
        this.workPlace=work;

    }

    public String getCity() {
        return city;
    }

    public float getMoney() {
        return money;
    }

    public float getFood() {
        return food;
    }

    public float getSleep() {
        return sleep;
    }

    public float getWater() {
        return water;
    }

    public String getTitle() {
        return title;
    }

    public String getUsername() {
        return username;
    }

    public void setProperties(ArrayList<Property> properties) {
        this.properties = properties;
    }

    public void setFood(float food) {
        this.food = food;
    }

    public void setWater(float water) {
        this.water = water;
    }

    public void setSleep(float sleep) {
        this.sleep = sleep;
    }

    public void setMoney(float money) {
        this.money = money;
    }

    public void AddProperties(Property property){
        properties.add(property);
    }


    Color black = new Color(0, 0, 0);
    Color almostblack = new Color(32, 32, 32);
    Color lightgray = new Color(160, 160, 160);
    Color lightred = new Color(222, 104, 104);
    Color pink = new Color(206, 114, 140);
    Color purple2 = new Color(243, 212, 153);
    Color blue2 = new Color(77, 133, 180);
    Color orange = new Color(199, 156, 100);
    Color white = new Color(255, 255, 255);
    Color blue = new Color(74, 110, 164);
    Color cream = new Color(255, 204, 153);
    Color red = new Color(190, 44, 47);
    Color darkRed = new Color(124, 11, 11);
    Color green = new Color(134, 13, 114);
    Color green1 = new Color(119, 213, 144);
    Color green3 = new Color(133, 223, 227);
    Color green2 = new Color(224, 182, 113);
    Color green4 = new Color(84, 36, 50);



    public void Myjob() throws IOException {
        File input_file = new File(
                "D:/tadaaa/java/Growing in LightCity/src/myjob1.jpg");

        BufferedImage myImage = ImageIO.read(input_file);

        JFrame frame = new JFrame("<"+city+">");
        ImagePanel imagePanel = new ImagePanel(myImage);
        frame.setContentPane(imagePanel);
        Font f1 = new Font(Font.SERIF, Font.BOLD, 18);
        if (!(title.equals("None"))) {
            JLabel l1 = new JLabel("My Job : ");
            l1.setBounds(30, 120, 150, 40);
            l1.setFont(f1);
            l1.setForeground(purple2);

            JLabel l2 = new JLabel(title);
            l2.setBounds(110, 120, 150, 40);
            l2.setFont(f1);
            l2.setForeground(lightred);

            JLabel l3 = new JLabel("Work Place : ");
            l3.setBounds(30, 170, 150, 40);
            l3.setFont(f1);
            l3.setForeground(purple2);

            JLabel l4 = new JLabel(workPlace);
            l4.setBounds(140, 170, 150, 40);
            l4.setFont(f1);
            l4.setForeground(lightred);

            JButton b1 = new JButton("Quit Job");
            b1.setBounds(5, 220, 120, 40);
            b1.setFont(f1);
            b1.setForeground(purple2);
            b1.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if(title.equals("Mayor")){
                        JOptionPane.showMessageDialog(frame,
                                "You Can't Quit Your Job",
                                " ",
                                JOptionPane.INFORMATION_MESSAGE);


                    }else{
                        Property property=Database.FindWorkingPlace(workPlace,city);

                        int num=property.getWorkerNum();
                        num++;
                        property.setWorkerNum(num);
                        JOptionPane.showMessageDialog(frame,
                                "You Quit Your Job",
                                " ",
                                JOptionPane.INFORMATION_MESSAGE);
                        Database.UpdateAvatar(username,city,"None","None",money,0,0,0,1);
                        Database.changeBuilding(property.getTitle(),property.getSalary(),property.getBusiness(),city,
                                property.getLocationX(),property.getLocationY(),property.getWorkCon(),property.getWorkerNum());

                        title="None";
                        workPlace="None";
                        try {
                            Myjob();
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                        frame.dispose();
                    }

                }
            });

            b1.setBorderPainted(false);
            b1.setFocusPainted(false);
            b1.setContentAreaFilled(false);
            frame.add(l1);
            frame.add(l2);
            frame.add(l3);
            frame.add(l4);
            frame.add(b1);

        } else {
            JLabel l1 = new JLabel("You Are Unemployed ");
            l1.setBounds(30, 170, 180, 40);
            l1.setFont(f1);
            l1.setForeground(lightred);

            JButton b1 = new JButton("Find Job");
            b1.setBounds(5, 220, 120, 40);
            b1.setFont(f1);
            b1.setForeground(purple2);
            b1.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    try {
                        findJob();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    frame.dispose();
                }
            });
            b1.setBorderPainted(false);
            b1.setFocusPainted(false);
            b1.setContentAreaFilled(false);
            frame.add(l1);
            frame.add(b1);

        }

        JButton b3 = new JButton("Exit");
        b3.setBounds(5, 260, 80, 40);
        b3.setFont(f1);
        b3.setForeground(purple2);
        b3.addActionListener(new ActionListener() {
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
                    Menu.Dashboard(Avatar.this);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                frame.dispose();
            }
        });

        b3.setBorderPainted(false);
        b3.setFocusPainted(false);
        b3.setContentAreaFilled(false);
        back.setBorderPainted(false);
        back.setFocusPainted(false);
        back.setContentAreaFilled(false);

        frame.add(b3);
        frame.add(back);
        frame.setSize(500, 500);
        frame.setLayout(null);
        frame.setVisible(true);

    }

    public void findJob()throws IOException{
        File input_file = new File(
                "D:/tadaaa/java/Growing in LightCity/src/findjob.png");

        BufferedImage myImage = ImageIO.read(input_file);

        JFrame frame = new JFrame("<"+city+">");
        ImagePanel imagePanel = new ImagePanel(myImage);
        frame.setContentPane(imagePanel);

        Font f1 = new Font(Font.SERIF, Font.BOLD, 18);
        Font f2 = new Font(Font.SERIF, Font.BOLD, 22);

        JLabel l1 = new JLabel("Available Jobs ");
        l1.setBounds(150, 20, 180, 40);
        l1.setFont(f2);
        l1.setForeground(almostblack);


        ArrayList<Property> value = Database.availableJobs(city);
        ArrayList<JButton> buttons=new ArrayList<>();
        ArrayList<JLabel> labels=new ArrayList<>();
        int x=10;
        int y=55;
        for (Property property : value) {

            JLabel label = new JLabel("Name : ");
            label.setBounds(x, y, 90, 40);
            label.setFont(f1);
            label.setForeground(almostblack);

            labels.add(label);
            if(property.getWorkerNum()==0){
                JButton button = new JButton(property.getTitle());
                button.setBounds(x+20, y, 120, 40);
                button.setFont(f1);
                button.setForeground(darkRed);

                button.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        JOptionPane.showMessageDialog(frame,
                                "This Place Already Has Workers",
                                " ",
                                JOptionPane.INFORMATION_MESSAGE);
                    }
                });

                button.setBorderPainted(false);
                button.setFocusPainted(false);
                button.setContentAreaFilled(false);

                buttons.add(button);
            }else{
                JButton button = new JButton(property.getTitle());
                button.setBounds(x+20, y, 120, 40);
                button.setFont(f1);
                button.setForeground(red);

                button.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        int num=property.getWorkerNum();
                        num--;
                        property.setWorkerNum(num);
                        Database.UpdateAvatar(username,city,"worker",property.getTitle(),0,0,0,0,1);
                        Database.changeBuilding(property.getTitle(),property.getSalary(),property.getBusiness(),city,
                                property.getLocationX(),property.getLocationY(),property.getWorkCon(),property.getWorkerNum());
                        JOptionPane.showMessageDialog(frame,
                                "You're Hired \n",
                                " ",
                                JOptionPane.INFORMATION_MESSAGE);
                        workPlace=property.getTitle();
                        title="worker";
                        try {
                            Myjob();
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                        frame.dispose();

                    }
                });
                button.setBorderPainted(false);
                button.setFocusPainted(false);
                button.setContentAreaFilled(false);

                buttons.add(button);
            }


            JLabel label1 = new JLabel("Business : "+property.getBusiness());
            label1.setBounds(x, y+30, 230, 40);
            label1.setFont(f1);
            label1.setForeground(almostblack);

            labels.add(label1);
            JLabel label2= new JLabel("Salary : "+property.getSalary());
            label2.setBounds(x+240, y+30, 200, 40);
            label2.setFont(f1);
            label2.setForeground(almostblack);

            labels.add(label2);


            y+=75;

        }

        JButton back = new JButton("<<<<");
        back.setBounds(0, 420, 90, 30);
        back.setFont(f1);
        back.setForeground(almostblack);
        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    Menu.Dashboard(Avatar.this);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                frame.dispose();
            }
        });

        back.setBorderPainted(false);
        back.setFocusPainted(false);
        back.setContentAreaFilled(false);

        frame.add(back);
        for (JButton button : buttons) {
            frame.add(button);
        }
        for (JLabel label : labels) {
            frame.add(label);
        }
        frame.add(l1);
        frame.setSize(500, 500);
        frame.setLayout(null);
        frame.setVisible(true);


    }

    public void Properties() throws IOException {
          File input_file = new File(
                "D:/tadaaa/java/Growing in LightCity/src/property.jpg");

        BufferedImage myImage = ImageIO.read(input_file);

        JFrame frame = new JFrame("<"+city+">");
        ImagePanel imagePanel = new ImagePanel(myImage);
        frame.setContentPane(imagePanel);
        Font f1 = new Font(Font.SERIF, Font.BOLD, 17);
        Font f2 = new Font(Font.SERIF, Font.BOLD, 22);

        JLabel l1 = new JLabel("Property");
        l1.setBounds(210, 80, 180, 40);
        l1.setFont(f2);
        l1.setForeground(blue);

        JButton b1 = new JButton("Show Properties");
        b1.setBounds(160, 125, 180, 40);
        b1.setFont(f1);
        b1.setForeground(black);
        b1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(properties.size()==0){
                    JOptionPane.showMessageDialog(frame,
                            "You Have No Properties",
                            " ",
                            JOptionPane.INFORMATION_MESSAGE);


                }else {
                    try {
                        ShowProperties(0);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    frame.dispose();
                }
            }
        });

        JButton b2 = new JButton("Sell");
        b2.setBounds(160, 175, 180, 40);
        b2.setFont(f1);
        b2.setForeground(black);
        b2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(properties.size()==0){
                    JOptionPane.showMessageDialog(frame,
                            "You Have No Properties",
                            " ",
                            JOptionPane.INFORMATION_MESSAGE);


                }else {
                    try {
                        ShowProperties(1);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    frame.dispose();
                }
            }
        });

        JButton b3 = new JButton("Management");
        b3.setBounds(160, 225, 180, 40);
        b3.setFont(f1);
        b3.setForeground(black);
        b3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(properties.size()==0){
                    JOptionPane.showMessageDialog(frame,
                            "You Have No Properties",
                            " ",
                            JOptionPane.INFORMATION_MESSAGE);


                }else {
                    try {
                        ShowProperties(2);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    frame.dispose();
                }
            }
        });

        JButton b4 = new JButton("Found Industry");
        b4.setBounds(160, 275, 180, 40);
        b4.setFont(f1);
        b4.setForeground(black);
        b4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    ShowProperties(3);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                frame.dispose();
            }
        });

        JButton b5 = new JButton("Exit");
        b5.setBounds(160, 325, 180, 40);
        b5.setFont(f1);
        b5.setForeground(black);
        b5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });

        JButton back = new JButton("<<<<");
        back.setBounds(0, 420, 90, 30);
        back.setFont(f1);
        back.setForeground(white);
        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    Menu.Dashboard(Avatar.this);
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
        b5.setBorderPainted(false);
        b5.setFocusPainted(false);
        b5.setContentAreaFilled(false);
        back.setBorderPainted(false);
        back.setFocusPainted(false);
        back.setContentAreaFilled(false);


        frame.add(l1);
        frame.add(b1);
        frame.add(b2);
        frame.add(b3);
        frame.add(b4);
        frame.add(b5);
        frame.add(back);
        frame.setSize(500, 500);
        frame.setLayout(null);
        frame.setVisible(true);

    }

    public void ShowProperties(int which)throws IOException{


        Database.buildingOwn(username,city,Avatar.this);

        File input_file = new File(
                "D:/tadaaa/java/Growing in LightCity/src/alaf.jpg");

        BufferedImage myImage = ImageIO.read(input_file);

        JFrame frame = new JFrame("<"+city+">");
        ImagePanel imagePanel = new ImagePanel(myImage);
        frame.setContentPane(imagePanel);

        Font f1 = new Font(Font.SERIF, Font.BOLD, 17);
        JLabel l1 = new JLabel("Money : ");
        l1.setBounds(340, 465, 180, 40);
        l1.setFont(f1);
        l1.setForeground(white);

        JLabel l2 = new JLabel(money+"$");
        l2.setBounds(405, 465, 180, 40);
        l2.setFont(f1);
        l2.setForeground(white);


            JButton b2 = new JButton(new ImageIcon("D:/tadaaa/java/Growing in LightCity/src/bank.png"));
            b2.setBounds(280, 190, 100, 100);
            b2.setFont(f1);
            b2.setForeground(lightgray);
            b2.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    JOptionPane.showMessageDialog(frame,
                            ":)))",
                            "Bank ",
                            JOptionPane.INFORMATION_MESSAGE);


                }
            });


            JButton b3 = new JButton(new ImageIcon("D:/tadaaa/java/Growing in LightCity/src/circle.png"));

            b3.setBounds(100, 190, 100, 100);
            b3.setFont(f1);
            b3.setForeground(lightgray);
            b3.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {

                    JOptionPane.showMessageDialog(frame,
                            ";)))",
                            "Town Hall ",
                            JOptionPane.INFORMATION_MESSAGE);

                }
            });

            b2.setBorderPainted(false);
            b2.setFocusPainted(false);
            b2.setContentAreaFilled(false);
            b3.setBorderPainted(false);
            b3.setFocusPainted(false);
            b3.setContentAreaFilled(false);

            frame.add(b2);frame.add(b3);




        ArrayList<JButton> buttons=new ArrayList<>();
        if(which==0){
            for (Property property : properties) {

                JButton button = new JButton(property.getTitle());
                button.setBounds(property.getLocationX(), property.getLocationY(), 90, 90);
                button.setFont(f1);
                button.setForeground(black);
                button.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        JOptionPane.showMessageDialog(frame,
                                "Business : "+property.getBusiness()+"\nIncome : "+property.getIncome(),
                                property.getTitle(),
                                JOptionPane.INFORMATION_MESSAGE);



                    }
                });
                buttons.add(button);
            }
        }
        else if (which==1) {
            for (Property property : properties) {

                JButton button = new JButton(property.getTitle());
                button.setBounds(property.getLocationX(), property.getLocationY(), 90, 90);
                button.setFont(f1);
                button.setForeground(black);

                JTextField textField = new JTextField ("0");
                button.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if(property.getSituation().equals("Not Sold")) {
                            int x = JOptionPane.showConfirmDialog(frame,
                                    "Business : "+property.getBusiness()+"\nIncome : "+property.getIncome()
                                           +"\n\nDo You Want To Sell This Land ? \n",
                                    property.getTitle(),
                                    JOptionPane.YES_NO_OPTION);

                            if (x == JOptionPane.YES_OPTION) {

                                JOptionPane.showMessageDialog(frame, textField,"Enter Price",JOptionPane.INFORMATION_MESSAGE);
                                Database.ReadyToSell(property.getLocationX(),property.getLocationY(),city,"Ready To Sell", Integer.parseInt(textField.getText()),username);
                                property.setSituation("Ready To Sell");
                                property.setPrice(Integer.parseInt(textField.getText()));
                                try {
                                    ShowProperties(1);
                                } catch (IOException ex) {
                                    throw new RuntimeException(ex);
                                }
                                frame.dispose();

                                frame.getContentPane().add(textField);
                            }
                        }else{
                            int x = JOptionPane.showConfirmDialog(frame,
                                    "Business : "+property.getBusiness()+"\nIncome : "+property.getIncome()
                                            +"\nPrice : "+property.getPrice()+"\n\nThis Land Is On Sale\n"+
                                            " Do You Still Want To Sell It ? \n",
                                    property.getTitle(),
                                    JOptionPane.YES_NO_OPTION);
                            if (x == JOptionPane.NO_OPTION) {
                                Database.ReadyToSell(property.getLocationX(),property.getLocationY(),city,"Not Sold",0,username);
                                property.setSituation("Not Sold");
                                property.setPrice(0);
                                try {
                                    ShowProperties(1);
                                } catch (IOException ex) {
                                    throw new RuntimeException(ex);
                                }
                                frame.dispose();
                            }
                        }
                    }
                });
                buttons.add(button);

            }
        }
        else if (which==2) {
            for (Property property : properties) {
                if( property.getSituation().equals("Not Sold")){
                    JButton button = new JButton(property.getTitle());
                    button.setBounds(property.getLocationX(), property.getLocationY(), 90, 90);
                    button.setFont(f1);
                    button.setForeground(black);
                    button.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {

                            int x = JOptionPane.showConfirmDialog(frame,
                                    "Business : "+property.getBusiness()+"\n\nDo You Want To Manage This Land ? \n",
                                    property.getTitle(),
                                    JOptionPane.YES_NO_OPTION);
                            if (x == JOptionPane.YES_OPTION) {
                                try {
                                    ManageBusiness(property);
                                } catch (IOException ex) {
                                    throw new RuntimeException(ex);
                                }
                                frame.dispose();
                            }
                        }

                    });
                    buttons.add(button);
                }
            }
        }
        else if (which==3) {
            ArrayList<Property> p=Database.OnSale(username,city);
            for (Property value : p) {
                JButton button = new JButton("On Sale");
                button.setBounds(value.getLocationX(), value.getLocationY(), 90, 90);
                button.setFont(f1);
                button.setForeground(almostblack);
                button.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        int x = JOptionPane.showConfirmDialog(frame,
                                "Former Business : " + value.getBusiness() + "\nIncome : " + value.getIncome()
                                        + "\nPrice : " + value.getPrice() + "\n\nDo You Want To Buy This Land ?",
                                value.getTitle(), JOptionPane.YES_NO_OPTION);

                        if (x == JOptionPane.YES_OPTION) {
                            if(money<value.getPrice()){
                                JOptionPane.showMessageDialog(frame,"You Don't Have Enough Money" ," ",JOptionPane.INFORMATION_MESSAGE);

                            }else{
                                Database.ReadyToSell(value.getLocationX(), value.getLocationY(),city,"Not Sold",0,username);
                                Database.UpdateAvatar(username,city,null,null,value.getPrice(),0,0,0,2);
                                Database.UpdateAvatar(value.getOwner(),city,null,null,value.getPrice(),0,0,0,3);
                                properties.add(Database.FindBuilding(value.getLocationX(), value.getLocationY(),city));
                                money-=value.getPrice();
                                value.setOwner(username);
                                value.setSituation("Not Sold");

                                try {
                                    ShowProperties(3);
                                } catch (IOException ex) {
                                    throw new RuntimeException(ex);
                                }
                                frame.dispose();

                            }

                        }


                    }
                });
                buttons.add(button);

            }
            for (Property property : properties) {
                if(property.getTitle().equals("Field") && property.getSituation().equals("Not Sold")){
                    JButton button = new JButton(property.getTitle());
                    button.setBounds(property.getLocationX(), property.getLocationY(), 90, 90);
                    button.setFont(f1);
                    button.setForeground(black);
                    button.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            int x = JOptionPane.showConfirmDialog(frame,
                                    "Business : "+property.getBusiness()+"\nIncome : "+property.getIncome()
                                            +"\n\nDo You Want To Start A Business ?",
                                    property.getTitle(), JOptionPane.YES_NO_OPTION);

                            if (x == JOptionPane.YES_OPTION) {
                                try {
                                    property.startBusiness(Avatar.this);
                                } catch (IOException ex) {
                                    throw new RuntimeException(ex);
                                }
                                frame.dispose();
                            }


                        }
                    });
                    buttons.add(button);
                } else if (property.getSituation().equals("Ready To Sell")) {
                    JButton button = new JButton(property.getTitle());
                    button.setBounds(property.getLocationX(), property.getLocationY(), 90, 90);
                    button.setFont(f1);
                    button.setForeground(lightgray);
                    button.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                           JOptionPane.showMessageDialog(frame,
                                    "Business : "+property.getBusiness()+"\nIncome : "+property.getIncome()
                                            +"\n\n You Put This Land On Sale",
                                    property.getTitle(), JOptionPane.INFORMATION_MESSAGE);




                        }
                    });
                    buttons.add(button);
                } else{
                    JButton button = new JButton(property.getTitle());
                    button.setBounds(property.getLocationX(), property.getLocationY(), 90, 90);
                    button.setFont(f1);
                    button.setForeground(black);
                    button.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            int x = JOptionPane.showConfirmDialog(frame,
                                    "Business : "+property.getBusiness()+"\nIncome : "+property.getIncome()
                                            +"\n\nDo You Want To Change The Business Plan ?",
                                    property.getTitle(), JOptionPane.YES_NO_OPTION);
                            if (x == JOptionPane.YES_OPTION) {
                                try {
                                    property.changeBusiness(Avatar.this);
                                } catch (IOException ex) {
                                    throw new RuntimeException(ex);
                                }
                                frame.dispose();
                            }


                        }
                    });
                    buttons.add(button);
                }



            }

        }

        JButton back = new JButton("<<<<");
        back.setBounds(0, 470, 80, 30);
        back.setFont(f1);
        back.setForeground(white);
        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                try {
                    Properties();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                frame.dispose();
            }
        });


        back.setBorderPainted(false);
        back.setFocusPainted(false);
        back.setContentAreaFilled(false);


        for (JButton button : buttons) {
            frame.add(button);
        }

        frame.add(l1);
        frame.add(l2);

        frame.add(back);
        frame.setSize(500, 535);
        frame.setLayout(null);
        frame.setVisible(true);

    }

    public void ManageBusiness(Property property)throws IOException{

        File input_file = new File(
                "D:/tadaaa/java/Growing in LightCity/src/management2.jpg");

        BufferedImage myImage = ImageIO.read(input_file);

        JFrame frame = new JFrame("<"+city+">");
        ImagePanel imagePanel = new ImagePanel(myImage);
        frame.setContentPane(imagePanel);


        Font f1 = new Font(Font.SERIF, Font.BOLD, 17);
        Font f2 = new Font(Font.SERIF, Font.BOLD, 22);


        JLabel l1 = new JLabel(property.getTitle());
        l1.setBounds(200, 35, 140, 40);
        l1.setFont(f2);
        l1.setForeground(blue2);

        JLabel l2 = new JLabel("Business :");
        l2.setBounds(35, 80, 80, 40);
        l2.setFont(f1);
        l2.setForeground(pink);

        JLabel l5 = new JLabel(property.getBusiness());
        l5.setBounds(120, 80, 180, 40);
        l5.setFont(f1);
        l5.setForeground(blue2);

        JLabel l3 = new JLabel("Income :");
        l3.setBounds(35, 120, 80, 40);
        l3.setFont(f1);
        l3.setForeground(pink);

        JLabel l6 = new JLabel(String.valueOf(property.getIncome()));
        l6.setBounds(120, 120, 80, 40);
        l6.setFont(f1);
        l6.setForeground(blue2);

        JLabel l8 = new JLabel("Salary :");
        l8.setBounds(35, 160, 80, 40);
        l8.setFont(f1);
        l8.setForeground(pink);

        JLabel l10 = new JLabel(String.valueOf(property.getSalary()));
        l10.setBounds(120, 160, 80, 40);
        l10.setFont(f1);
        l10.setForeground(blue2);
        JButton b3 = new JButton(new ImageIcon("D:/tadaaa/java/Growing in LightCity/src/edit.png"));

        JTextField textField = new JTextField (String.valueOf(property.getSalary()));
        b3.setBounds(160, 172, 20, 20);
        b3.setFont(f1);
        b3.setForeground(lightgray);
        b3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(property.getTitle().equals("Field")){
                    JOptionPane.showMessageDialog(frame,
                            "You Can't Edit This ",
                            " ",
                            JOptionPane.INFORMATION_MESSAGE);
                }else{
                    JOptionPane.showMessageDialog(frame, textField, "Edit Salary", JOptionPane.INFORMATION_MESSAGE);
                    Database.changeBuilding(property.getTitle(), Integer.parseInt(textField.getText()),
                            property.getBusiness(),city,property.getLocationX(),property.getLocationY(),
                            property.getWorkCon(),property.getWorkerNum());
                    property.setSalary(Integer.parseInt(textField.getText()));
                    frame.getContentPane().add(textField);
                    try {
                        ManageBusiness(property);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    frame.dispose();
                }
            }
        });

        JButton b2 = new JButton("Workers");

        b2.setBounds(15, 200, 100, 60);
        b2.setFont(f1);
        b2.setForeground(blue2);
        ArrayList<Avatar> worker=Database.workerList(city,property.getTitle());

        b2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(property.getTitle().equals("Field")){
                    JOptionPane.showMessageDialog(frame,
                            "You Have No Worker",
                            " ",
                            JOptionPane.INFORMATION_MESSAGE);


                } else if (worker.size()==0) {
                    JOptionPane.showMessageDialog(frame,
                            "You Have No Worker",
                            " ",
                            JOptionPane.INFORMATION_MESSAGE);

                } else{
                    try {
                        workerList(property);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    frame.dispose();
                }
            }
        });



        JButton back = new JButton("<<<<");
        back.setBounds(0, 420, 90, 30);
        back.setFont(f1);
        back.setForeground(blue2);
        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    ShowProperties(2);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                frame.dispose();
            }
        });

        b2.setBorderPainted(false);
        b2.setFocusPainted(false);
        b2.setContentAreaFilled(false);
        back.setBorderPainted(false);
        back.setFocusPainted(false);
        back.setContentAreaFilled(false);

        frame.add(l1);
        frame.add(l2);
        frame.add(l3);
        frame.add(l5);
        frame.add(l6);
        frame.add(l8);
        frame.add(l10);
        frame.add(b3);
        frame.add(b2);
        frame.add(back);

        frame.setSize(500, 500);
        frame.setLayout(null);
        frame.setVisible(true);

    }

    public void workerList(Property property)throws IOException{
        File input_file = new File(
                "D:/tadaaa/java/Growing in LightCity/src/list.jpg");

        BufferedImage myImage = ImageIO.read(input_file);

        JFrame frame = new JFrame("<"+city+">");
        ImagePanel imagePanel = new ImagePanel(myImage);
        frame.setContentPane(imagePanel);

        ArrayList<Avatar> worker=Database.workerList(city,property.getTitle());

        Font f1 = new Font(Font.SERIF, Font.BOLD, 17);
        Font f2 = new Font(Font.SERIF, Font.BOLD, 22);

        JLabel l1 = new JLabel("Worker List ");
        l1.setBounds(180, 15, 180, 40);
        l1.setFont(f2);
        l1.setForeground(lightgray);




        ArrayList<JButton> buttons=new ArrayList<>();
        ArrayList<JLabel> labels=new ArrayList<>();
        int x=10;
        int y=55;
        for (Avatar avatar : worker) {

            JLabel label = new JLabel("Name : ");
            label.setBounds(x+110, y, 90, 40);
            label.setFont(f1);
            label.setForeground(lightgray);

            labels.add(label);

            JButton button = new JButton(avatar.username);
            button.setBounds(x+130, y, 120, 40);
            button.setFont(f1);
            button.setForeground(black);

            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    int x = JOptionPane.showConfirmDialog(frame,
                            "Do You Want To Fire This Worker ? ",
                            " ",
                            JOptionPane.YES_NO_OPTION);
                    if (x == JOptionPane.YES_OPTION) {
                        Database.UpdateAvatar(avatar.username,city,"None","None",0,0,0,0,1);
                        if(worker.size()==1){
                            try {
                                ManageBusiness(property);
                            } catch (IOException ex) {
                                throw new RuntimeException(ex);
                            }
                            frame.dispose();
                        }else {
                            try {
                                workerList(property);
                            } catch (IOException ex) {
                                throw new RuntimeException(ex);
                            }
                            frame.dispose();

                        }

                    }
                }
            });
            button.setBorderPainted(false);
            button.setFocusPainted(false);
            button.setContentAreaFilled(false);

            buttons.add(button);
            y+=45;

        }

        JButton back = new JButton("<<<<");
        back.setBounds(0, 420, 90, 30);
        back.setFont(f1);
        back.setForeground(lightgray);
        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                   ManageBusiness(property);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                frame.dispose();
            }
        });

        back.setBorderPainted(false);
        back.setFocusPainted(false);
        back.setContentAreaFilled(false);

        frame.add(back);
        for (JButton button : buttons) {
            frame.add(button);
        }
        for (JLabel label : labels) {
            frame.add(label);
        }
        frame.add(l1);
        frame.setSize(500, 500);
        frame.setLayout(null);
        frame.setVisible(true);

    }

    public void Economy() throws IOException {
        File input_file = new File(
                "D:/tadaaa/java/Growing in LightCity/src/economy.jpg");

        BufferedImage myImage = ImageIO.read(input_file);

        JFrame frame = new JFrame("<"+city+">");
        ImagePanel imagePanel = new ImagePanel(myImage);
        frame.setContentPane(imagePanel);
        Font f1 = new Font(Font.SERIF, Font.BOLD, 17);
        Font f2 = new Font(Font.SERIF, Font.BOLD, 22);

        JLabel l1 = new JLabel("Economy");
        l1.setBounds(175, 35, 140, 40);
        l1.setFont(f2);
        l1.setForeground(cream);

        JButton b1 = new JButton("Show Incomes");
        b1.setBounds(3, 110, 170, 40);
        b1.setFont(f1);
        b1.setForeground(white);
        b1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    showIncome();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                frame.dispose();
            }
        });

        JButton b2 = new JButton("Show Job Detail");
        b2.setBounds(5, 160, 180, 40);
        b2.setFont(f1);
        b2.setForeground(white);
        b2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(title.equals("None")){
                    JOptionPane.showMessageDialog(frame,
                            "You Have No Job",
                            " ",
                            JOptionPane.INFORMATION_MESSAGE);


                }else {
                    try {
                        ShowJobDetail();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    frame.dispose();
                }

            }
        });

        JButton b3 = new JButton("How Can Grow Up ?");
        b3.setBounds(11, 210, 200, 40);
        b3.setFont(f1);
        b3.setForeground(white);
        b3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {


            }
        });

        JButton b4 = new JButton("Exit");
        b4.setBounds(11, 260, 80, 40);
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
        back.setForeground(black);
        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    Menu.Dashboard(Avatar.this);
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

    public void showIncome()throws IOException{
        File input_file = new File(
                "D:/tadaaa/java/Growing in LightCity/src/income1.jpg");

        BufferedImage myImage = ImageIO.read(input_file);

        JFrame frame = new JFrame("<"+city+">");
        ImagePanel imagePanel = new ImagePanel(myImage);
        frame.setContentPane(imagePanel);

        Font f1 = new Font(Font.SERIF, Font.BOLD, 17);
        Font f2 = new Font(Font.SERIF, Font.BOLD, 22);

        JLabel l = new JLabel("Income");
        l.setBounds(180, 15, 180, 40);
        l.setFont(f2);
        l.setForeground(almostblack);


        if(title.equals("Mayor")){
            JLabel l1 = new JLabel("Job Income : ");
            l1.setBounds(10, 65, 180, 40);
            l1.setFont(f1);
            l1.setForeground(almostblack);

            JLabel l7 = new JLabel("1000$ ");
            l7.setBounds(110, 65, 180, 40);
            l7.setFont(f1);
            l7.setForeground(black);

            frame.add(l1);
            frame.add(l7);
        } else if (title.equals("None")) {
            JLabel l7 = new JLabel("You Have No Job");
            l7.setBounds(10, 65, 180, 40);
            l7.setFont(f1);
            l7.setForeground(darkRed);

        } else{
            JLabel l1 = new JLabel("Job Income : ");
            l1.setBounds(10, 65, 180, 40);
            l1.setFont(f1);
            l1.setForeground(almostblack);

            JLabel l7 = new JLabel(Database.GetSalary(city,workPlace)+"$ ");
            l7.setBounds(110, 65, 180, 40);
            l7.setFont(f1);
            l7.setForeground(black);

            frame.add(l1);
            frame.add(l7);
        }
        int y=105;
        ArrayList<JLabel> labels=new ArrayList<>();
        for(Property property: properties){
            if(!(property.getTitle().equals("Field"))){
                JLabel l3 = new JLabel("Building Name :");
                l3.setBounds(10, y, 180, 40);
                l3.setFont(f1);
                l3.setForeground(almostblack);
                labels.add(l3);

                JLabel l4 = new JLabel(property.getTitle());
                l4.setBounds(140, y, 180, 40);
                l4.setFont(f1);
                l4.setForeground(black);
                labels.add(l4);

                JLabel l5 = new JLabel("Income : ");
                l5.setBounds(200, y, 180, 40);
                l5.setFont(f1);
                l5.setForeground(almostblack);
                labels.add(l5);

                JLabel l6 = new JLabel(String.valueOf(property.getIncome()));
                l6.setBounds(270, y, 180, 40);
                l6.setFont(f1);
                l6.setForeground(black);
                labels.add(l6);

                y+=40;

            }
        }


        JButton back = new JButton("<<<<");
        back.setBounds(0, 410, 90, 30);
        back.setFont(f1);
        back.setForeground(black);
        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    Economy();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                frame.dispose();
            }
        });


        back.setBorderPainted(false);
        back.setFocusPainted(false);
        back.setContentAreaFilled(false);

        for (JLabel label:labels){
            frame.add(label);
        }
        frame.add(l);
        frame.add(back);
        frame.setSize(500, 500);
        frame.setLayout(null);
        frame.setVisible(true);

    }

    public void ShowJobDetail() throws IOException {
        File input_file = new File(
                "D:/tadaaa/java/Growing in LightCity/src/jobDetail.jpg");

        BufferedImage myImage = ImageIO.read(input_file);

        JFrame frame = new JFrame("<"+city+">");
        ImagePanel imagePanel = new ImagePanel(myImage);
        frame.setContentPane(imagePanel);

        Font f1 = new Font(Font.SERIF, Font.BOLD, 17);
        Font f2 = new Font(Font.SERIF, Font.BOLD, 22);



        JLabel l1 = new JLabel("Job :");
        l1.setBounds(20, 145, 180, 40);
        l1.setFont(f1);
        l1.setForeground(black);

        JLabel l2 = new JLabel(title);
        l2.setBounds(60, 145, 180, 40);
        l2.setFont(f1);
        l2.setForeground(black);

        JLabel l3 = new JLabel("Work Place :");
        l3.setBounds(20, 200, 180, 40);
        l3.setFont(f1);
        l3.setForeground(black);

        JLabel l4 = new JLabel(workPlace);
        l4.setBounds(120, 200, 180, 40);
        l4.setFont(f1);
        l4.setForeground(black);

        JLabel l5 = new JLabel("Salary : ");
        l5.setBounds(20, 250, 180, 40);
        l5.setFont(f1);
        l5.setForeground(black);

        if(title.equals("Mayor")){
            JLabel l6 = new JLabel("1000$ ");
            l6.setBounds(80, 250, 180, 40);
            l6.setFont(f1);
            l6.setForeground(black);

            frame.add(l6);
        }else {
            JLabel l6 = new JLabel(Database.GetSalary(city,workPlace)+"$ ");
            l6.setBounds(80, 250, 180, 40);
            l6.setFont(f1);
            l6.setForeground(black);

            frame.add(l6);
        }




        JButton back = new JButton("<<<<");
        back.setBounds(0, 420, 90, 30);
        back.setFont(f1);
        back.setForeground(black);
        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                   Economy();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                frame.dispose();
            }
        });


        back.setBorderPainted(false);
        back.setFocusPainted(false);
        back.setContentAreaFilled(false);

        frame.add(l1);
        frame.add(l2);
        frame.add(l3);
        frame.add(l4);
        frame.add(l5);
        frame.add(back);
        frame.setSize(500, 500);
        frame.setLayout(null);
        frame.setVisible(true);

    }

    public void Life() throws IOException {

       File input_file = new File(
                "D:/tadaaa/java/Growing in LightCity/src/life.jpg");

        BufferedImage myImage = ImageIO.read(input_file);

        JFrame frame = new JFrame("<"+city+">");
        ImagePanel imagePanel = new ImagePanel(myImage);
        frame.setContentPane(imagePanel);

        Font f1 = new Font(Font.SERIF, Font.BOLD, 17);
        Font f2 = new Font(Font.SERIF, Font.BOLD, 22);

        JLabel l1 = new JLabel("Life ");
        l1.setBounds(220, 35, 180, 40);
        l1.setFont(f2);
        l1.setForeground(orange);

        JLabel l2 = new JLabel("Sleep :");
        l2.setBounds(35, 80, 80, 40);
        l2.setFont(f1);
        l2.setForeground(lightred);

        JLabel l5 = new JLabel(String.valueOf(sleep));
        l5.setBounds(100, 80, 80, 40);
        l5.setFont(f1);
        l5.setForeground(orange);

        JLabel l3 = new JLabel("Food :");
        l3.setBounds(35, 120, 80, 40);
        l3.setFont(f1);
        l3.setForeground(lightred);

        JLabel l6 = new JLabel(String.valueOf(food));
        l6.setBounds(100, 120, 80, 40);
        l6.setFont(f1);
        l6.setForeground(orange);

        JLabel l4 = new JLabel("Water :");
        l4.setBounds(35, 160, 80, 40);
        l4.setFont(f1);
        l4.setForeground(lightred);

        JLabel l7 = new JLabel(String.valueOf(water));
        l7.setBounds(100, 160, 80, 40);
        l7.setFont(f1);
        l7.setForeground(orange);

        JButton b2 = new JButton("Sleep Function");
        b2.setBounds(0, 210, 180, 40);
        b2.setFont(f1);
        b2.setForeground(lightred);
        b2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(sleep == 10){
                    JOptionPane.showMessageDialog(frame,
                            "Your Sleep Is Already Full",
                            " ",
                            JOptionPane.INFORMATION_MESSAGE);
                }else{
                    Database.UpdateAvatar(username,city,title,workPlace,money,food,water,10,4);
                    sleep=10;
                    JOptionPane.showMessageDialog(frame,
                            "Sleep Full <----->",
                            " ",
                            JOptionPane.INFORMATION_MESSAGE);
                    try {
                        Life();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    frame.dispose();

                }

            }
        });

        JButton b3 = new JButton("Eat Function");
        b3.setBounds(0, 260, 170, 40);
        b3.setFont(f1);
        b3.setForeground(lightred);
        b3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    showFoodShops();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                frame.dispose();
            }
        });

        JButton b4 = new JButton("Exit");
        b4.setBounds(13, 310, 80, 40);
        b4.setFont(f1);
        b4.setForeground(lightred);
        b4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                frame.dispose();
            }
        });

        JButton back = new JButton("<<<<");
        back.setBounds(0, 420, 90, 30);
        back.setFont(f1);
        back.setForeground(orange);
        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    Menu.AvatarMenu(Avatar.this);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                frame.dispose();
            }
        });

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
        frame.add(l2);
        frame.add(l3);
        frame.add(l4);
        frame.add(l5);
        frame.add(l6);
        frame.add(l7);

        frame.add(b2);
        frame.add(b3);
        frame.add(b4);
        frame.add(back);
        frame.setSize(500, 500);
        frame.setLayout(null);
        frame.setVisible(true);

    }

    public void showFoodShops() throws IOException {

        File input_file = new File(
                "D:/tadaaa/java/Growing in LightCity/src/foodshop1.jpg");

        BufferedImage myImage = ImageIO.read(input_file);

        JFrame frame = new JFrame("<"+city+">");
        ImagePanel imagePanel = new ImagePanel(myImage);
        frame.setContentPane(imagePanel);

        Font f1 = new Font(Font.SERIF, Font.BOLD, 18);



        ArrayList<Property> value =Database.foodShop(city);
        ArrayList<JButton> buttons=new ArrayList<>();
        ArrayList<JLabel> labels=new ArrayList<>();
        int x=15;
        int y=18;
        for (Property property : value) {

            JLabel label = new JLabel("Name : ");
            label.setBounds(x, y, 80, 40);
            label.setFont(f1);
            label.setForeground(almostblack);

            labels.add(label);


                JButton button = new JButton(property.getTitle());
                button.setBounds(x+30, y, 130, 40);
                button.setFont(f1);
                button.setForeground(green);

                button.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {

                        if(property.getBusiness().equals("Restaurant")){

                            try {
                                property.goInsideRestaurant(Avatar.this,2);
                            } catch (IOException ex) {
                                throw new RuntimeException(ex);
                            }
                            frame.dispose();

                        }else {

                            try {
                                property.goInsideSuperMarket(Avatar.this,2);
                            } catch (IOException ex) {
                                throw new RuntimeException(ex);
                            }
                            frame.dispose();

                        }
                        frame.dispose();

                    }
                });
                button.setBorderPainted(false);
                button.setFocusPainted(false);
                button.setContentAreaFilled(false);

                buttons.add(button);



            JLabel label1 = new JLabel("Business : "+property.getBusiness());
            label1.setBounds(x, y+25, 230, 40);
            label1.setFont(f1);
            label1.setForeground(almostblack);

            labels.add(label1);

            y+=57;

        }

        JButton back = new JButton("<<<<");
        back.setBounds(0, 420, 90, 30);
        back.setFont(f1);
        back.setForeground(green);
        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    Menu.Dashboard(Avatar.this);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                frame.dispose();
            }
        });

        back.setBorderPainted(false);
        back.setFocusPainted(false);
        back.setContentAreaFilled(false);

        frame.add(back);
        for (JButton button : buttons) {
            frame.add(button);
        }
        for (JLabel label : labels) {
            frame.add(label);
        }

        frame.setSize(500, 500);
        frame.setLayout(null);
        frame.setVisible(true);


    }

    public void GoTo() throws IOException{

        File input_file = new File(
                "D:/tadaaa/java/Growing in LightCity/src/alaf.jpg");

        BufferedImage myImage = ImageIO.read(input_file);

        JFrame frame = new JFrame("<"+city+">");
        ImagePanel imagePanel = new ImagePanel(myImage);
        frame.setContentPane(imagePanel);

        Font f1 = new Font(Font.SERIF, Font.BOLD, 17);


        JButton b2 = new JButton(new ImageIcon("D:/tadaaa/java/Growing in LightCity/src/bank.png"));
        b2.setBounds(280, 190, 100, 100);
        b2.setFont(f1);
        b2.setForeground(lightgray);
        b2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int x =JOptionPane.showConfirmDialog(frame,
                        "Do You Want To Go In ?",
                        "Bank ",
                        JOptionPane.YES_NO_OPTION);
                if(x == JOptionPane.YES_OPTION){

                }
            }
        });


        JButton b3 = new JButton(new ImageIcon("D:/tadaaa/java/Growing in LightCity/src/circle.png"));

        b3.setBounds(100, 190, 100, 100);
        b3.setFont(f1);
        b3.setForeground(lightgray);
        b3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(title.equals("Mayor")){
                    JOptionPane.showMessageDialog(frame,
                            "You Own This Building",
                            "Town Hall ",
                            JOptionPane.INFORMATION_MESSAGE);

                }else{
                    JOptionPane.showMessageDialog(frame,
                            "Mayor : "+Database.FindMayor(city)+"\n",
                            "Town Hall ",
                            JOptionPane.INFORMATION_MESSAGE);


                }

            }
        });

        ArrayList<JButton> buttons=new ArrayList<>();
        for(int x=15;x<=375; x=x+90) {
            for (int y = 15; y <= 375; y = y + 90) {
                if ((x + 90) == y || (y + 90) == x || (x + 270) == y || (y + 270) == x || (x == 195 && y == 195)) {
                    continue;
                }
                JButton button = new JButton(Database.FindBuilding(x,y,city).getTitle());
                button.setBounds(x, y, 90, 90);
                button.setFont(f1);
                button.setForeground(black);
                button.setBorderPainted(false);
                int finalX = x;
                int finalY = y;

                if (Database.FindBuilding(finalX, finalY, city).getTitle().equals("Field")){
                    button.setBackground(green1);

                }else if(Database.FindBuilding(finalX, finalY, city).getBusiness().equals("Restaurant")){
                    button.setBackground(green2);

                }else if (Database.FindBuilding(finalX, finalY, city).getBusiness().equals("Super Market")){
                    button.setBackground(green3);

                }else{
                    button.setBackground(green4);

                }


                button.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if (Database.FindBuilding(finalX, finalY, city).getTitle().equals("Field")){
                            JOptionPane.showMessageDialog(frame,
                                    "Empty Field \n",
                                     " ",
                                    JOptionPane.INFORMATION_MESSAGE);

                        }else{
                            if(Database.FindBuilding(finalX, finalY, city).getBusiness().equals("Restaurant")){

                                try {
                                    Database.FindBuilding(finalX, finalY, city).goToRestaurant(Avatar.this);
                                } catch (IOException ex) {
                                    throw new RuntimeException(ex);
                                }
                                frame.dispose();

                            }else if (Database.FindBuilding(finalX, finalY, city).getBusiness().equals("Super Market")){

                                try {
                                    Database.FindBuilding(finalX, finalY, city).goToSuperMarket(Avatar.this);
                                } catch (IOException ex) {
                                    throw new RuntimeException(ex);
                                }
                                frame.dispose();

                            }else{
                                int x = JOptionPane.showConfirmDialog(frame,
                                        "Do You Want To Work ? ",
                                        " ",
                                        JOptionPane.YES_NO_OPTION);
                                if (x == JOptionPane.YES_OPTION) {
                                    if(Database.checkWorkingPlace(city,username,workPlace)){
                                        if(water>3 || food>3 || sleep>3){
                                            water-=2;
                                            food-=2;
                                            sleep-=2;
                                            JOptionPane.showMessageDialog(frame,
                                                    "Water : "+water+"\nFood : "+food+"\nSleep : "+sleep,
                                                    "Your States",
                                                    JOptionPane.INFORMATION_MESSAGE);

                                            Database.UpdateAvatar(username,city,title,workPlace,money,food,water,sleep,4);

                                        }else {
                                            JOptionPane.showMessageDialog(frame,
                                                    "You Don't Have Enough Energy",
                                                    " ",
                                                    JOptionPane.INFORMATION_MESSAGE);
                                        }

                                    }else {
                                        JOptionPane.showMessageDialog(frame,
                                                "You Don't Work Here ",
                                                " ",
                                                JOptionPane.INFORMATION_MESSAGE);
                                    }

                                }
                            }

                        }
                    }
                });
                buttons.add(button);
            }


        }
        JButton back = new JButton("<<<<");
        back.setBounds(0, 470, 80, 30);
        back.setFont(f1);
        back.setForeground(white);
        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    Menu.AvatarMenu(Avatar.this);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                frame.dispose();
            }
        });
        frame.setBackground(black);
        JLabel l1=new JLabel("Restaurant");
        l1.setForeground(green2);
        l1.setBounds(15, 500, 160, 30);

        JLabel l2=new JLabel("Super Market");
        l2.setForeground(green3);
        l2.setBounds(210, 500, 160, 30);

        JLabel l3=new JLabel("Food Factory");
        l3.setForeground(green4);
        l3.setBounds(380, 500, 160, 30);

        b2.setBorderPainted(false);
        b2.setFocusPainted(false);
        b2.setContentAreaFilled(false);
        b3.setBorderPainted(false);
        b3.setFocusPainted(false);
        b3.setContentAreaFilled(false);
        back.setBorderPainted(false);
        back.setFocusPainted(false);
        back.setContentAreaFilled(false);

        frame.add(b2);frame.add(b3);

        for (JButton button : buttons) {
            frame.add(button);
        }

        frame.add(back);
        frame.add(l1);
        frame.add(l2);
        frame.add(l3);
        frame.setSize(500, 570);
        frame.setLayout(null);
        frame.setVisible(true);

    }



}
