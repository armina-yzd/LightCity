import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Property {
    private String title;
    private final String city;
    private String owner;
    private String business;
    private int income;
    private int price;
    private int salary;
    private final int locationX;
    private final int locationY;
    private String situation;
    private String WorkCon;
    private int workerNum;


    public String getOwner() {
        return owner;
    }

    public int getLocationX() {
        return locationX;
    }

    public int getLocationY() {
        return locationY;
    }

    public String getTitle() {
        return title;
    }

    public String getBusiness() {
        return business;
    }

    public int getPrice() {
        return price;
    }

    public String getSituation() {
        return situation;
    }

    public String getWorkCon() {
        return WorkCon;
    }

    public int getWorkerNum() {
        return workerNum;
    }

    public void setSituation(String situation) {
        this.situation = situation;
    }

    public void setWorkerNum(int workerNum) {
        this.workerNum = workerNum;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public int getSalary() {
        return salary;
    }

    public int getIncome() {
        return income;
    }

    public Property(String title, String city, String owner, String business, int income, int price,
                    int salary, int locationX, int locationY, String situation,String workCon,int workerNum){
        this.title=title;
        this.city=city;
        this.owner=owner;
        this.business=business;
        this.income=income;
        this.price=price;
        this.salary=salary;
        this.locationX=locationX;
        this.locationY=locationY;
        this.situation=situation;
        this.WorkCon=workCon;
        this.workerNum=workerNum;
    }


    static Color black = new Color(0, 0, 0);
    static Color almostblack = new Color(192, 192, 192);
    static Color lightgray = new Color(160, 160, 160);
    static Color gray = new Color(128, 128, 128);
    static Color blue = new Color(74, 110, 164);
    static Color orange = new Color(210, 123, 56);
    static Color yellow = new Color(231, 168, 76);
    static Color white1 = new Color(208, 204, 160);
    static Color white2 = new Color(190, 187, 142);

    public void startBusiness(Avatar avatar) throws IOException {
        File input_file = new File(
                "D:/tadaaa/java/Growing in LightCity/src/business.png");

        BufferedImage myImage = ImageIO.read(input_file);

        JFrame frame = new JFrame("<"+city+">");
        ImagePanel imagePanel = new ImagePanel(myImage);
        frame.setContentPane(imagePanel);

        final JLabel label = new JLabel();

        final JTextField text = new JTextField();
        label.setBounds(125, 55, 130, 35);
        text.setBounds(160, 55, 130, 30);


        Font f1 = new Font(Font.SERIF, Font.BOLD, 16);
        Font f2 = new Font(Font.SERIF, Font.BOLD, 20);

        JLabel l2 = new JLabel("Money :  "+avatar.getMoney()+"$");
        l2.setBounds(355, 415, 200, 40);
        l2.setFont(f1);
        l2.setForeground(blue);

        JLabel l1 = new JLabel(" Shop Name :   ");
        l1.setBounds(55, 50, 150, 40);
        l1.setFont(f1);
        l1.setForeground(black);

        JLabel l3 = new JLabel("Do You Want to Have Workers ?   ");
        l3.setBounds(60, 100, 250, 40);
        l3.setFont(f1);
        l3.setForeground(black);


        JButton r1 = new JButton("Yes");
        JButton r2 = new JButton("No");

        WorkCon="No";
        r1.setBounds(290, 110, 60, 20);
        JTextField textField = new JTextField ("100");
        JTextField textField1 = new JTextField ("1");
        r1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                JOptionPane.showMessageDialog(frame, textField, "Enter Salary", JOptionPane.INFORMATION_MESSAGE);

                frame.getContentPane().add(textField);
                JOptionPane.showMessageDialog(frame, textField1, "Enter Worker Number", JOptionPane.INFORMATION_MESSAGE);

                frame.getContentPane().add(textField1);

                r1.setForeground(blue);
                r2.setForeground(black);
                WorkCon="Yes";
                workerNum= Integer.parseInt(textField1.getText());

            }
        });


        r2.setBounds(330, 110, 50, 20);
        r2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                r2.setForeground(blue);
                r1.setForeground(black);
                WorkCon="No";
                workerNum=0;
            }
        });

        JLabel l = new JLabel("  Business   ");
        l.setBounds(55, 170, 150, 40);
        l.setFont(f1);
        l.setForeground(black);
        String[] busi = {"Super Market (300$)","Food Company (800$)","Restaurant (500$)"};
        final JComboBox<String> cb = new JComboBox<>(busi);
        cb.setBounds(145, 180, 160, 25);

        JButton b = new JButton("  Submit  ");
        b.setBounds(140, 250, 150, 40);
        b.setFont(f2);
        b.setForeground(blue);
        final String[] shop = {null};
        b.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(cb.getSelectedItem().equals("Super Market (300$)")){
                    shop[0] ="Super Market";
                    if(avatar.getMoney()<300){
                        JOptionPane.showMessageDialog(frame,"You Don't Have Enough Money" ," ",JOptionPane.INFORMATION_MESSAGE);
                        try {
                            startBusiness(avatar);
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                        frame.dispose();
                        return;
                    }

                } else if (cb.getSelectedItem().equals("Food Company (800$)")) {
                    shop[0] ="Food Company";
                    if(avatar.getMoney()<800){
                        JOptionPane.showMessageDialog(frame,"You Don't Have Enough Money" ," ",JOptionPane.INFORMATION_MESSAGE);
                        try {
                            startBusiness(avatar);
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                        frame.dispose();
                        return;
                    }

                }else{
                    shop[0] ="Restaurant";
                    if(avatar.getMoney()<500){
                        JOptionPane.showMessageDialog(frame,"You Don't Have Enough Money" ," ",JOptionPane.INFORMATION_MESSAGE);
                        try {
                            startBusiness(avatar);
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                        frame.dispose();
                        return;
                    }

                }

                if (Database.changeBusiness(text.getText(),city, Integer.parseInt(textField.getText()), shop[0],locationX,locationY,WorkCon,workerNum)) {
                    JOptionPane.showMessageDialog(frame,"You Built The Building Successfully" ," ",JOptionPane.INFORMATION_MESSAGE);
                    business=shop[0];
                    title=text.getText();
                    if(cb.getSelectedItem().equals("Super Market (300$)")){

                        Database.UpdateAvatar(owner,city,null,null,300,0,0,0,2);
                        float num=avatar.getMoney();
                        num-=300;
                        avatar.setMoney(num);

                    } else if (cb.getSelectedItem().equals("Food Company (800$)")) {

                        Database.UpdateAvatar(owner,city,null,null,800,0,0,0,2);
                        float num=avatar.getMoney();
                        num-=800;
                        avatar.setMoney(num);
                    }else{

                        Database.UpdateAvatar(owner,city,null,null,500,0,0,0,2);
                        float num=avatar.getMoney();
                        num-=500;
                        avatar.setMoney(num);

                    }

                    try {
                        avatar.ShowProperties(3);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    frame.dispose();
                    return;

                }


                int x = JOptionPane.showConfirmDialog(frame,
                        "There Is Already a Building With The Same Name \n Do You Want To Try Again?",
                        " ",
                        JOptionPane.YES_NO_OPTION);
                if (x == JOptionPane.YES_OPTION) {
                    try {
                        startBusiness(avatar);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    frame.dispose();

                } else {
                    try {
                        avatar.ShowProperties(3);
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
        back.setForeground(blue);
        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    avatar.ShowProperties(3);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                frame.dispose();
            }
        });

        r1.setBorderPainted(false);
        r1.setFocusPainted(false);
        r1.setContentAreaFilled(false);
        r2.setBorderPainted(false);
        r2.setFocusPainted(false);
        r2.setContentAreaFilled(false);
        b.setBorderPainted(false);
        b.setFocusPainted(false);
        b.setContentAreaFilled(false);
        back.setBorderPainted(false);
        back.setFocusPainted(false);
        back.setContentAreaFilled(false);

        frame.add(back);

        frame.add(l1);
        frame.add(l2);
        frame.add(l3);
        frame.add(r1);
        frame.add(r2);
        frame.add(l);
        frame.add(cb);
        frame.add(label);
        frame.add(b);
        frame.add(textField);
        frame.add(text);
        frame.setSize(500, 500);
        frame.setLayout(null);
        frame.setVisible(true);

    }

    public void changeBusiness(Avatar avatar) throws IOException {

        File input_file = new File(
                "D:/tadaaa/java/Growing in LightCity/src/business.png");

        BufferedImage myImage = ImageIO.read(input_file);

        JFrame frame = new JFrame("<"+city+">");
        ImagePanel imagePanel = new ImagePanel(myImage);
        frame.setContentPane(imagePanel);

        ArrayList<Avatar> avatars=Database.workerList(city,title);


        Font f1 = new Font(Font.SERIF, Font.BOLD, 16);
        Font f2 = new Font(Font.SERIF, Font.BOLD, 20);

        JLabel l2 = new JLabel("Money :  "+avatar.getMoney()+"$");
        l2.setBounds(355, 415, 200, 40);
        l2.setFont(f1);
        l2.setForeground(blue);

        JLabel l8 = new JLabel("Name : ");
        l8.setBounds(60, 50, 70, 40);
        l8.setFont(f1);
        l8.setForeground(black);

        JLabel l10 = new JLabel(String.valueOf(title));
        l10.setBounds(120, 50, 80, 40);
        l10.setFont(f1);
        l10.setForeground(blue);

        JButton b3 = new JButton(new ImageIcon("D:/tadaaa/java/Growing in LightCity/src/edit.png"));
        b3.setBounds(180, 62, 20, 20);
        JTextField text = new JTextField (title);
        b3.setFont(f1);
        b3.setForeground(lightgray);
        b3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                        JOptionPane.showMessageDialog(frame, text, "Enter New Name", JOptionPane.INFORMATION_MESSAGE);
                        frame.getContentPane().add(text);


            }
        });

        JLabel l3 = new JLabel("Do You Want to Have Workers ?   ");
        l3.setBounds(60, 100, 250, 40);
        l3.setFont(f1);
        l3.setForeground(black);

        JButton r1 = new JButton("Yes");
        JButton r2 = new JButton("No");


        r1.setBounds(290, 110, 60, 20);
        JTextField textField2 = new JTextField ("100");
        JTextField textField1 = new JTextField ("1");
        r1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                JOptionPane.showMessageDialog(frame, textField2, "Enter Salary", JOptionPane.INFORMATION_MESSAGE);
                frame.getContentPane().add(textField2);

                JOptionPane.showMessageDialog(frame, textField1, "Enter Worker Number", JOptionPane.INFORMATION_MESSAGE);
                frame.getContentPane().add(textField1);

                r1.setForeground(blue);
                r2.setForeground(black);

                WorkCon="Yes";
                workerNum= Integer.parseInt(textField1.getText());
                salary=Integer.parseInt(textField2.getText());

            }
        });


        r2.setBounds(330, 110, 50, 20);
        r2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                r2.setForeground(blue);
                r1.setForeground(black);
                WorkCon="No";
                workerNum=0;
                salary=0;
            }
        });

        JLabel l = new JLabel("  Business   ");
        l.setBounds(55, 170, 150, 40);
        l.setFont(f1);
        l.setForeground(black);

        String[] busi = {"Super Market (300$)","Food Company (800$)","Restaurant (500$)"};
        final JComboBox<String> cb = new JComboBox<>(busi);
        cb.setBounds(145, 180, 160, 25);

        JButton b = new JButton("  Submit  ");
        b.setBounds(140, 250, 150, 40);
        b.setFont(f2);
        b.setForeground(blue);
        final String[] shop = {null};
        b.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String b;
                if(business.equals("Super Market")){
                    b="Super Market (300$)";
                } else if (business.equals("Food Company ")) {
                    b="Food Company (800$)";
                }else {
                    b="Restaurant (500$)";
                }

                if(cb.getSelectedItem().equals(b)){
                    if (Database.changeBusiness(text.getText(),city, salary, shop[0],locationX,locationY,WorkCon,workerNum) || text.getText().equals(title)) {
                        JOptionPane.showMessageDialog(frame,"You Changed The Building Successfully" ," ",JOptionPane.INFORMATION_MESSAGE);
                        Database.changeBuilding(text.getText(),salary,business,city,locationX,locationY,WorkCon,workerNum);
                        business=shop[0];
                        title=text.getText();
                        for(Avatar avatar1 : avatars){
                            Database.UpdateAvatar(avatar1.getUsername(),city,"worker",text.getText(),0,0,0,0,1);
                        }
                        try {
                            avatar.ShowProperties(3);
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                        frame.dispose();
                        return;

                    }


                    int x = JOptionPane.showConfirmDialog(frame,
                            "There Is Already a Building With The Same Name \n Do You Want To Try Again?",
                            " ",
                            JOptionPane.YES_NO_OPTION);
                    if (x == JOptionPane.YES_OPTION) {
                        try {
                            changeBusiness(avatar);
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                        frame.dispose();

                    } else {
                        try {
                            avatar.ShowProperties(3);
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                        frame.dispose();

                    }

                    return;
                }

                if(cb.getSelectedItem().equals("Super Market (300$)")){
                    shop[0] ="Super Market";
                    if(avatar.getMoney()<300){
                        JOptionPane.showMessageDialog(frame,"You Don't Have Enough Money" ," ",JOptionPane.INFORMATION_MESSAGE);
                        try {
                            changeBusiness(avatar);
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                        frame.dispose();
                        return;
                    }

                } else if (cb.getSelectedItem().equals("Food Company (800$)")) {
                    shop[0] ="Food Company";
                    if(avatar.getMoney()<800){
                        JOptionPane.showMessageDialog(frame,"You Don't Have Enough Money" ," ",JOptionPane.INFORMATION_MESSAGE);
                        try {
                            changeBusiness(avatar);
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                        frame.dispose();
                        return;
                    }

                }else{
                    shop[0] ="Restaurant";
                    if(avatar.getMoney()<500){
                        JOptionPane.showMessageDialog(frame,"You Don't Have Enough Money" ," ",JOptionPane.INFORMATION_MESSAGE);
                        try {
                            changeBusiness(avatar);
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                        frame.dispose();
                        return;
                    }

                }

                if (Database.changeBusiness(text.getText(),city, Integer.parseInt(textField1.getText()),
                        shop[0],locationX,locationY,WorkCon,workerNum) || text.getText().equals(title)) {

                    Database.changeBuilding(text.getText(),salary,shop[0],city,locationX,locationY,WorkCon,workerNum);
                    business=shop[0];
                    title=text.getText();

                    for(Avatar avatar1 : avatars){
                        Database.UpdateAvatar(avatar1.getUsername(),city,"worker",text.getText(),0,0,0,0,1);
                    }
                    JOptionPane.showMessageDialog(frame,"You Changed The Building Successfully" ," ",JOptionPane.INFORMATION_MESSAGE);
                    if(cb.getSelectedItem().equals("Super Market (300$)")){

                        Database.UpdateAvatar(owner,city,null,null,300,0,0,0,2);
                        float num=avatar.getMoney();
                        num-=300;
                        avatar.setMoney(num);

                    } else if (cb.getSelectedItem().equals("Food Company (800$)")) {

                        Database.UpdateAvatar(owner,city,null,null,800,0,0,0,2);
                        float num=avatar.getMoney();
                        num-=800;
                        avatar.setMoney(num);
                    }else{

                        Database.UpdateAvatar(owner,city,null,null,500,0,0,0,2);
                        float num=avatar.getMoney();
                        num-=500;
                        avatar.setMoney(num);

                    }

                    try {
                        avatar.ShowProperties(3);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    frame.dispose();
                    return;

                }


                int x = JOptionPane.showConfirmDialog(frame,
                        "There Is Already a Building With The Same Name \n Do You Want To Try Again?",
                        " ",
                        JOptionPane.YES_NO_OPTION);
                if (x == JOptionPane.YES_OPTION) {
                    try {
                        changeBusiness(avatar);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    frame.dispose();

                } else {
                    try {
                        avatar.ShowProperties(3);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    frame.dispose();

                }
                frame.dispose();
            }
        });


        JButton back = new JButton("<<<<");
        back.setBounds(0, 420, 90, 30);
        back.setFont(f1);
        back.setForeground(blue);
        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    avatar.ShowProperties(3);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                frame.dispose();
            }
        });

        r1.setBorderPainted(false);
        r1.setFocusPainted(false);
        r1.setContentAreaFilled(false);
        r2.setBorderPainted(false);
        r2.setFocusPainted(false);
        r2.setContentAreaFilled(false);
        b.setBorderPainted(false);
        b.setFocusPainted(false);
        b.setContentAreaFilled(false);
        back.setBorderPainted(false);
        back.setFocusPainted(false);
        back.setContentAreaFilled(false);

        frame.add(back);
        frame.add(r1);
        frame.add(r2);
        frame.add(l3);
        frame.add(l2);
        frame.add(l8);
        frame.add(l10);
        frame.add(l);
        frame.add(b);
        frame.add(cb);
        frame.add(b3);
        frame.setSize(500, 500);
        frame.setLayout(null);
        frame.setVisible(true);

    }

    public void goToRestaurant(Avatar avatar) throws IOException {
        File input_file = new File(
                "D:/tadaaa/java/Growing in LightCity/src/restaurant1.png");

        BufferedImage myImage = ImageIO.read(input_file);

        JFrame frame = new JFrame("<"+city+">");
        ImagePanel imagePanel = new ImagePanel(myImage);
        frame.setContentPane(imagePanel);

        Font f1 = new Font(Font.SERIF, Font.BOLD, 19);
        Font f2 = new Font(Font.SERIF, Font.BOLD, 24);

        JLabel l1 = new JLabel("Welcome To "+title);
        l1.setBounds(160, 95, 250, 40);
        l1.setFont(f2);
        l1.setForeground(orange);

        JButton b1 = new JButton("Order Food");
        b1.setBounds(148, 185, 200, 40);
        b1.setFont(f1);
        b1.setForeground(yellow);
        b1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    goInsideRestaurant(avatar,1);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                frame.dispose();
            }
        });

        JButton b2 = new JButton("Work");
        b2.setBounds(170, 250, 150, 40);
        b2.setFont(f1);
        b2.setForeground(yellow);
        b2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(Database.checkWorkingPlace(city,avatar.getUsername(),title)){
                    if(avatar.getWater()>3 && avatar.getFood()>3 && avatar.getSleep()>3){
                        float water=avatar.getWater();
                        float food=avatar.getFood();
                        float sleep=avatar.getSleep();
                        food-=2;
                        water-=2;
                        sleep-=2;
                        avatar.setFood(food);
                        avatar.setWater(water);
                        avatar.setSleep(sleep);

                        JOptionPane.showMessageDialog(frame,
                                "Water : "+water+"\nFood : "+food+"\nSleep : "+sleep,
                                "Your States",
                                JOptionPane.INFORMATION_MESSAGE);

                        Database.UpdateAvatar(avatar.getUsername(),city,avatar.getTitle(),title,avatar.getMoney()
                                ,food,water,sleep,4);

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
        });




        JButton back = new JButton("<<<<");
        back.setBounds(75, 390, 90, 30);
        back.setFont(f1);
        back.setForeground(orange);
        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    avatar.GoTo();
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
        back.setBorderPainted(false);
        back.setFocusPainted(false);
        back.setContentAreaFilled(false);

        frame.add(back);
        frame.add(l1);
        frame.add(b1);
        frame.add(b2);
        frame.setSize(500, 500);
        frame.setLayout(null);
        frame.setVisible(true);

    }

    public void goToSuperMarket(Avatar avatar) throws IOException {
        File input_file = new File(
                "D:/tadaaa/java/Growing in LightCity/src/supermarket1.jpg");

        BufferedImage myImage = ImageIO.read(input_file);
        JFrame frame = new JFrame("<"+city+">");
        ImagePanel imagePanel = new ImagePanel(myImage);
        frame.setContentPane(imagePanel);

        Font f1 = new Font(Font.SERIF, Font.BOLD, 19);
        Font f2 = new Font(Font.SERIF, Font.BOLD, 24);

        JLabel l1 = new JLabel("Welcome To " + title);
        l1.setBounds(140, 60, 250, 40);
        l1.setFont(f2);
        l1.setForeground(black);

        JButton b1 = new JButton("Buy Groceries");
        b1.setBounds(120, 145, 200, 40);
        b1.setFont(f1);
        b1.setForeground(black);
        b1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    goInsideSuperMarket(avatar,1);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                frame.dispose();
            }
        });

        JButton b2 = new JButton("Work");
        b2.setBounds(142, 220, 150, 40);
        b2.setFont(f1);
        b2.setForeground(black);
        b2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(Database.checkWorkingPlace(city,avatar.getUsername(),title)){
                    if(avatar.getWater()>3 && avatar.getFood()>3 && avatar.getSleep()>3){
                        float water=avatar.getWater();
                        float food=avatar.getFood();
                        float sleep=avatar.getSleep();
                        food-=2;
                        water-=2;
                        sleep-=2;
                        avatar.setFood(food);
                        avatar.setWater(water);
                        avatar.setSleep(sleep);

                        JOptionPane.showMessageDialog(frame,
                                "Water : "+water+"\nFood : "+food+"\nSleep : "+sleep,
                                "Your States",
                                JOptionPane.INFORMATION_MESSAGE);

                        Database.UpdateAvatar(avatar.getUsername(),city,avatar.getTitle(),title,avatar.getMoney()
                                ,food,water,sleep,4);

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
        });

        JButton back = new JButton("<<<<");
        back.setBounds(0, 420, 90, 30);
        back.setFont(f1);
        back.setForeground(lightgray);
        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    avatar.GoTo();
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
        back.setBorderPainted(false);
        back.setFocusPainted(false);
        back.setContentAreaFilled(false);

        frame.add(back);
        frame.add(l1);
        frame.add(b1);
        frame.add(b2);
        frame.setSize(500, 500);
        frame.setLayout(null);
        frame.setVisible(true);

    }

    public void goInsideRestaurant(Avatar avatar,int choice) throws IOException {

        File input_file = new File(
                "D:/tadaaa/java/Growing in LightCity/src/restaurant.jpg");

        BufferedImage myImage = ImageIO.read(input_file);
        JFrame frame = new JFrame("<"+city+">");
        ImagePanel imagePanel = new ImagePanel(myImage);
        frame.setContentPane(imagePanel);

        Font f1 = new Font(Font.SERIF, Font.BOLD, 17);
        Font f2 = new Font(Font.SERIF, Font.BOLD, 22);

        JLabel l1 = new JLabel("Welcome To "+title);
        l1.setBounds(130, 35, 250, 40);
        l1.setFont(f2);
        l1.setForeground(white2);

        JLabel l = new JLabel("Money : ");
        l.setBounds(345, 415, 180, 40);
        l.setFont(f1);
        l.setForeground(white1);

        JLabel l2 = new JLabel(avatar.getMoney()+"$");
        l2.setBounds(410, 415, 180, 40);
        l2.setFont(f1);
        l2.setForeground(white1);


        JButton b1 = new JButton("French Fries 5$");
        b1.setBounds(10, 135, 190, 40);
        b1.setFont(f1);
        b1.setForeground(white1);
        b1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(avatar.getMoney()<5){
                    JOptionPane.showMessageDialog(frame,"You Don't Have Enough Money" ," ",JOptionPane.INFORMATION_MESSAGE);
                    try {
                        goInsideRestaurant(avatar,choice);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    frame.dispose();

                }else{
                    Database.UpdateAvatar(avatar.getUsername(),city,null,null,5,0,0,0,2);
                    income+=5;
                    Database.buildingIncome(locationX,locationY,city,income);
                    float food = avatar.getFood();
                    float sleep = avatar.getSleep();
                    float water = avatar.getWater();

                    food+=3;

                    if(food>10){
                        food=10;
                    }
                    avatar.setFood(food);
                    Database.UpdateAvatar(avatar.getUsername(),city,null,null,0,food,water,sleep,4);
                    JOptionPane.showMessageDialog(frame,
                            "Water : "+water+"\nFood : "+food+"\nSleep : "+sleep,
                            "Your States",
                            JOptionPane.INFORMATION_MESSAGE);

                    float money=avatar.getMoney();
                    money-=5;
                    avatar.setMoney(money);
                    try {
                        goInsideRestaurant(avatar,choice);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    frame.dispose();

                }

            }
        });

        JButton b2 = new JButton("Hamburger 10$");
        b2.setBounds(15, 185, 180, 40);
        b2.setFont(f1);
        b2.setForeground(white1);
        b2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(avatar.getMoney()<10){
                    JOptionPane.showMessageDialog(frame,"You Don't Have Enough Money" ," ",JOptionPane.INFORMATION_MESSAGE);

                }else{
                    Database.UpdateAvatar(avatar.getUsername(),city,null,null,10,0,0,0,2);
                    income+=10;
                    Database.buildingIncome(locationX,locationY,city,income);

                    float food = avatar.getFood();
                    float sleep = avatar.getSleep();
                    float water = avatar.getWater();

                    food+=5;

                    if(food>10){
                        food=10;
                    }
                    avatar.setFood(food);
                    Database.UpdateAvatar(avatar.getUsername(),city,null,null,0,food,water,sleep,4);
                    JOptionPane.showMessageDialog(frame,
                            "Water : "+water+"\nFood : "+food+"\nSleep : "+sleep,
                            "Your States",
                            JOptionPane.INFORMATION_MESSAGE);

                    float money=avatar.getMoney();
                    money-=10;
                    avatar.setMoney(money);
                    try {
                        goInsideRestaurant(avatar,choice);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    frame.dispose();
                }
            }
        });

        JButton b3 = new JButton("Pizza 15$");
        b3.setBounds(15, 235, 140, 40);
        b3.setFont(f1);
        b3.setForeground(white1);
        b3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(avatar.getMoney()<15){
                    JOptionPane.showMessageDialog(frame,"You Don't Have Enough Money" ," ",JOptionPane.INFORMATION_MESSAGE);

                }else{
                    Database.UpdateAvatar(avatar.getUsername(),city,null,null,15,0,0,0,2);
                    income+=15;
                    Database.buildingIncome(locationX,locationY,city,income);

                    float food = avatar.getFood();
                    float sleep = avatar.getSleep();
                    float water = avatar.getWater();

                    food+=7;

                    if(food>10){
                        food=10;
                    }
                    avatar.setFood(food);
                    Database.UpdateAvatar(avatar.getUsername(),city,null,null,0,food,water,sleep,4);
                    JOptionPane.showMessageDialog(frame,
                            "Water : "+water+"\nFood : "+food+"\nSleep : "+sleep,
                            "Your States",
                            JOptionPane.INFORMATION_MESSAGE);
                    float money=avatar.getMoney();
                    money-=15;
                    avatar.setMoney(money);
                    try {
                        goInsideRestaurant(avatar,choice);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    frame.dispose();
                }

            }
        });

        JButton b4 = new JButton("Beer 5$");
        b4.setBounds(15, 285, 145, 40);
        b4.setFont(f1);
        b4.setForeground(white1);
        b4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(avatar.getMoney()<12){
                    JOptionPane.showMessageDialog(frame,"You Don't Have Enough Money" ," ",JOptionPane.INFORMATION_MESSAGE);

                }else{
                    Database.UpdateAvatar(avatar.getUsername(),city,null,null,12,0,0,0,2);
                    income+=12;
                    Database.buildingIncome(locationX,locationY,city,income);

                    float food = avatar.getFood();
                    float sleep = avatar.getSleep();
                    float water = avatar.getWater();

                    water+=2;

                    if(water>10){
                        water=10;
                    }
                    avatar.setFood(food);
                    Database.UpdateAvatar(avatar.getUsername(),city,null,null,0,food,water,sleep,4);
                    JOptionPane.showMessageDialog(frame,
                            "Water : "+water+"\nFood : "+food+"\nSleep : "+sleep,
                            "Your States",
                            JOptionPane.INFORMATION_MESSAGE);
                    float money=avatar.getMoney();
                    money-=12;
                    avatar.setMoney(money);
                    try {
                        goInsideRestaurant(avatar,choice);
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
        back.setForeground(white1);
        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(choice==1){
                    try {
                        goToRestaurant(avatar);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }else{
                    try {
                        avatar.showFoodShops();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
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

        frame.add(back);
        frame.add(l1);
        frame.add(l);
        frame.add(l2);
        frame.add(b1);
        frame.add(b2);
        frame.add(b3);
        frame.add(b4);
        frame.setSize(500, 500);
        frame.setLayout(null);
        frame.setVisible(true);


    }

    public void goInsideSuperMarket(Avatar avatar,int choice) throws IOException {
        File input_file = new File(
                "D:/tadaaa/java/Growing in LightCity/src/supermarket.jpg");

        BufferedImage myImage = ImageIO.read(input_file);
        JFrame frame = new JFrame("<"+city+">");
        ImagePanel imagePanel = new ImagePanel(myImage);
        frame.setContentPane(imagePanel);

        Font f1 = new Font(Font.SERIF, Font.BOLD, 17);
        Font f2 = new Font(Font.SERIF, Font.BOLD, 22);

        JLabel l = new JLabel("Money : ");
        l.setBounds(345, 415, 180, 40);
        l.setFont(f1);
        l.setForeground(black);

        JLabel l2 = new JLabel(avatar.getMoney()+"$");
        l2.setBounds(410, 415, 180, 40);
        l2.setFont(f1);
        l2.setForeground(black);


        JLabel l1 = new JLabel("Welcome To "+title);
        l1.setBounds(165, 35, 250, 40);
        l1.setFont(f2);
        l1.setForeground(black);

        JButton b1 = new JButton("Milk 2$");
        b1.setBounds(186, 100, 130, 40);
        b1.setFont(f1);
        b1.setForeground(black);
        b1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(avatar.getMoney()<2){
                    JOptionPane.showMessageDialog(frame,"You Don't Have Enough Money" ," ",JOptionPane.INFORMATION_MESSAGE);

                }else{
                    Database.UpdateAvatar(avatar.getUsername(),city,null,null,2,0,0,0,2);
                    income+=2;
                    Database.buildingIncome(locationX,locationY,city,income);
                    float food = avatar.getFood();
                    float sleep = avatar.getSleep();
                    float water = avatar.getWater();

                    water+=3;

                    if(water>10){
                        water=10;
                    }
                    avatar.setWater(water);
                    Database.UpdateAvatar(avatar.getUsername(),city,null,null,0,food,water,sleep,4);
                    JOptionPane.showMessageDialog(frame,
                            "Water : "+water+"\nFood : "+food+"\nSleep : "+sleep,
                            "Your States",
                            JOptionPane.INFORMATION_MESSAGE);

                    float money=avatar.getMoney();
                    money-=2;
                    avatar.setMoney(money);
                    try {
                        goInsideSuperMarket(avatar,choice);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    frame.dispose();
                }

            }
        });

        JButton b2 = new JButton("Chips 3$");
        b2.setBounds(186, 150, 130, 40);
        b2.setFont(f1);
        b2.setForeground(black);
        b2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(avatar.getMoney()<3){
                    JOptionPane.showMessageDialog(frame,"You Don't Have Enough Money" ," ",JOptionPane.INFORMATION_MESSAGE);

                }else{
                    Database.UpdateAvatar(avatar.getUsername(),city,null,null,3,0,0,0,2);
                    income+=3;
                    Database.buildingIncome(locationX,locationY,city,income);

                    float food = avatar.getFood();
                    float sleep = avatar.getSleep();
                    float water = avatar.getWater();

                    food+=1;

                    if(food>10){
                        food=10;
                    }
                    avatar.setFood(food);

                    Database.UpdateAvatar(avatar.getUsername(),city,null,null,0,food,water,sleep,4);
                    JOptionPane.showMessageDialog(frame,
                            "Water : "+water+"\nFood : "+food+"\nSleep : "+sleep,
                            "Your States",
                            JOptionPane.INFORMATION_MESSAGE);

                    float money=avatar.getMoney();
                    money-=3;
                    avatar.setMoney(money);
                    try {
                        goInsideSuperMarket(avatar,choice);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    frame.dispose();
                }
            }
        });

        JButton b3 = new JButton("Water 1$");
        b3.setBounds(182, 200, 140, 40);
        b3.setFont(f1);
        b3.setForeground(black);
        b3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(avatar.getMoney()<1){
                    JOptionPane.showMessageDialog(frame,"You Don't Have Enough Money" ," ",JOptionPane.INFORMATION_MESSAGE);

                }else{
                    Database.UpdateAvatar(avatar.getUsername(),city,null,null,1,0,0,0,2);
                    income+=1;
                    Database.buildingIncome(locationX,locationY,city,income);

                    float food = avatar.getFood();
                    float sleep = avatar.getSleep();
                    float water = avatar.getWater();

                    water+=1;

                    if(water>10){
                        water=10;
                    }
                    avatar.setWater(water);

                    Database.UpdateAvatar(avatar.getUsername(),city,null,null,0,food,water,sleep,4);
                    JOptionPane.showMessageDialog(frame,
                            "Water : "+water+"\nFood : "+food+"\nSleep : "+sleep,
                            "Your States",
                            JOptionPane.INFORMATION_MESSAGE);

                    float money=avatar.getMoney();
                    money-=1;
                    avatar.setMoney(money);
                    try {
                        goInsideSuperMarket(avatar,choice);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    frame.dispose();
                }
            }
        });

        JButton b4 = new JButton("Biscuit 4$");
        b4.setBounds(182, 250, 145, 40);
        b4.setFont(f1);
        b4.setForeground(black);
        b4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(avatar.getMoney()<4){
                    JOptionPane.showMessageDialog(frame,"You Don't Have Enough Money" ," ",JOptionPane.INFORMATION_MESSAGE);

                }else{
                    Database.UpdateAvatar(avatar.getUsername(),city,null,null,4,0,0,0,2);
                    income+=4;
                    Database.buildingIncome(locationX,locationY,city,income);

                    float food = avatar.getFood();
                    float sleep = avatar.getSleep();
                    float water = avatar.getWater();

                    food+=2;

                    if(food>10){
                        food=10;
                    }
                    avatar.setFood(food);

                    Database.UpdateAvatar(avatar.getUsername(),city,null,null,0,food,water,sleep,4);
                    JOptionPane.showMessageDialog(frame,
                            "Water : "+water+"\nFood : "+food+"\nSleep : "+sleep,
                            "Your States",
                            JOptionPane.INFORMATION_MESSAGE);

                    float money=avatar.getMoney();
                    money-=4;
                    avatar.setMoney(money);
                    try {
                        goInsideSuperMarket(avatar,choice);
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
        back.setForeground(black);
        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(choice==1){
                    try {
                        goToSuperMarket(avatar);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }else{
                    try {
                        avatar.showFoodShops();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
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

        frame.add(back);
        frame.add(l1);
        frame.add(l);
        frame.add(l2);
        frame.add(b1);
        frame.add(b2);
        frame.add(b3);
        frame.add(b4);
        frame.setSize(500, 500);
        frame.setLayout(null);
        frame.setVisible(true);


    }

}
