package minesweep;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;
import javax.swing.BorderFactory;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.Border;

/**
 * Author: Tosby
 */
public final class MineSweep implements ActionListener, MouseListener {

    private JFrame screen = null;
    private JButton smiley = new JButton("");
    private JLabel points = new JLabel("0");
    private JLabel dev = new JLabel("");
    private JPanel composite = new JPanel();
    private JPanel topPanel = new JPanel();
    private JPanel bottomPanel = new JPanel();
    Font font = new Font("Serif", Font.BOLD, 18);
    Font devFont = new Font("Serif", Font.PLAIN, 12);
    Border border = BorderFactory.createLineBorder(Color.blue);
    Dimension preferredItmSize = new Dimension(30, 30);

    ImageIcon smileyImageIcon = null;
    ImageIcon tImageIcon = null;
    ImageIcon pitImageIcon = null;
    ImageIcon lossImageIcon = null;
    ImageIcon cryImageIcon = null;
    ImageIcon oneImageIcon = null;
    ImageIcon twoImageIcon = null;
    ImageIcon threeImageIcon = null;
    ImageIcon fourImageIcon = null;
    ImageIcon fiveImageIcon = null;

    String text = "";
    int score = 0;

    public MineSweep() {
        screen = new JFrame("MineSweeper by Tosby, otemainc.com");
        screen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        screen.setVisible(true);
        screen.setResizable(false);
        screen.setJMenuBar(createMenuBar());
        loadMinesweeperImages();

        composite.setLayout(new BorderLayout());
        smiley.setPreferredSize(preferredItmSize);
        smiley.setIcon(smileyImageIcon);
        points.setOpaque(true);
        points.setBorder(border);
        points.setBackground(Color.green);
        points.setForeground(Color.blue);
        points.setFont(font);
        points.setPreferredSize(preferredItmSize);

        dev.setOpaque(true);
        dev.setBorder(border);
        dev.setForeground(Color.blue);
        dev.setFont(devFont);

        topPanel.add(smiley);
        topPanel.add(points);
        composite.add(topPanel, BorderLayout.NORTH);
        bottomPanel.add(dev);
        composite.add(bottomPanel, BorderLayout.AFTER_LAST_LINE);
        smiley.addActionListener(this);
        smiley.addMouseListener(this);
        arrangeButtons();
        screen.add(composite);
        screen.pack();
    }

    public void loadMinesweeperImages() {
        smileyImageIcon = getScaledImage("C:\\Users\\MASENO\\OneDrive\\Documents\\NetBeansProjects\\Minesweep\\src\\res\\smiley.png");
        tImageIcon = getScaledImage("C:\\Users\\MASENO\\OneDrive\\Documents\\NetBeansProjects\\Minesweep\\src\\res\\t.png");
        pitImageIcon = getScaledImage("C:\\Users\\MASENO\\OneDrive\\Documents\\NetBeansProjects\\Minesweep\\src\\res\\pit.png");
        lossImageIcon = getScaledImage("C:\\Users\\MASENO\\OneDrive\\Documents\\NetBeansProjects\\Minesweep\\src\\res\\lose.png");
        cryImageIcon = getScaledImage("C:\\Users\\MASENO\\OneDrive\\Documents\\NetBeansProjects\\Minesweep\\src\\res\\cry.png");
        oneImageIcon = getScaledImage("C:\\Users\\MASENO\\OneDrive\\Documents\\NetBeansProjects\\Minesweep\\src\\res\\1.png");
        twoImageIcon = getScaledImage("C:\\Users\\MASENO\\OneDrive\\Documents\\NetBeansProjects\\Minesweep\\src\\res\\2.png");
        threeImageIcon = getScaledImage("C:\\Users\\MASENO\\OneDrive\\Documents\\NetBeansProjects\\Minesweep\\src\\res\\3.png");
        fourImageIcon = getScaledImage("C:\\Users\\MASENO\\OneDrive\\Documents\\NetBeansProjects\\Minesweep\\src\\res\\4.png");
        fiveImageIcon = getScaledImage("C:\\Users\\MASENO\\OneDrive\\Documents\\NetBeansProjects\\Minesweep\\src\\res\\5.png");

    }

    public JMenuBar createMenuBar() {

        JMenuBar mBar = new JMenuBar();
        JMenu game = new JMenu("Game");

        JMenu help = new JMenu("Help");

        final JMenuItem miNew = new JMenuItem("New");
        final JMenuItem miBeg = new JMenuItem("Beginner");
        final JMenuItem miInter = new JMenuItem("Intermediate");
        final JMenuItem miExp = new JMenuItem("Expert");
        final JMenuItem miExit = new JMenuItem("Exit");

        final JMenuItem about = new JMenuItem("About MineSweeper....");

        game.add(miNew);
        game.add(miBeg);
        game.add(miInter);
        game.add(miExp);
        game.add(miExit);

        help.add(about);

        ActionListener MENULSTNR = new ActionListener() {

            public void actionPerformed(ActionEvent ae) {
                if (miNew == ae.getSource()) {
                    btnwdth = 15;
                    btnhgt = 15;
                    mines = 10;
                    reset();
                }
                if (miBeg == ae.getSource()) {
                    btnwdth = 17;
                    btnhgt = 17;
                    mines = 12;
                    reset();

                }
                if (miInter == ae.getSource()) {
                    btnwdth = 20;
                    btnhgt = 20;
                    mines = 50;
                    reset();

                }
                if (miExp == ae.getSource()) {
                    btnwdth = 29;
                    btnhgt = 35;
                    mines = 80;
                    reset();
                }
                if (miExit == ae.getSource()) {
                    if (screen != null) {
                        screen.dispose();
                    }
                    System.exit(0);

                }

                if (about == ae.getSource()) {
                    text = " Designed and developped by Tosby Of Otema Technologies";
                    dev.setText(text);
                }
            }

        };

        miNew.addActionListener(MENULSTNR);
        miBeg.addActionListener(MENULSTNR);
        miInter.addActionListener(MENULSTNR);
        miExp.addActionListener(MENULSTNR);
        miExit.addActionListener(MENULSTNR);
        about.addActionListener(MENULSTNR);
        mBar.add(game);
        mBar.add(help);
        return mBar;
    }

    private int btnwdth = 15;

    private int btnhgt = 15;

    private int mines = 10;

    int mineArray[][];

    JButton button[][];
    JPanel minespan = null;

    public void arrangeButtons() {
        mineArray = new int[btnwdth][btnhgt];
        button = new JButton[btnwdth][btnhgt];
        boolean starting = true;
        if (minespan != null) {
            composite.remove(minespan);
            minespan = null;
            starting = false;

        }
        minespan = new JPanel();
        minespan.setLayout(new GridLayout(btnwdth, btnhgt));

        for (int i = 0; i < btnwdth; i++) {
            for (int j = 0; j < btnhgt; j++) {
                mineArray[i][j] = 0;
                button[i][j] = new JButton("");
                button[i][j].setBackground(Color.WHITE);
                button[i][j].setPreferredSize(new Dimension(16, 16));
                button[i][j].addActionListener(this);
                button[i][j].addMouseListener(this);
                minespan.add(button[i][j]);
            }
        }

        minespan.setVisible(true);
        composite.add(minespan, BorderLayout.CENTER);
        if (starting) {
            minesFormat(button);
        }
        screen.pack();
    }

    public void reset() {
        smiley.setIcon(smileyImageIcon);
        // btnwdth+=1;
        arrangeButtons();
        for (int i = 0; i < btnwdth; i++) {
            for (int j = 0; j < btnhgt; j++) {
                mineArray[i][j] = 0;
                button[i][j].addActionListener(this);
                button[i][j].addMouseListener(this);
                button[i][j].setText("");
                button[i][j].setBackground(Color.WHITE);
                //button[i][j].setIcon(tImageIcon);
            }
        }
        minesFormat(button);
        System.out.println("");
        System.out.println("");
    }

    public void minesFormat(JButton button[][]) {
        int mine[] = getRndmNos(btnwdth, btnhgt, mines);
        int count = 1;
        for (int i = 0; i < btnwdth; i++) {
            for (int j = 0; j < btnhgt; j++) {

                for (int k = 0; k < mine.length && mine[k] != 0; k++) {
                    if (count == mine[k]) {
                        mineArray[i][j] = 9;
                    }
                }
                count++;
            }
        }

        int boxcount = 0;
        for (int i = 0; i < btnwdth; i++) {
            for (int j = 0; j < btnhgt; j++) {
                boxcount = 0;

                if (mineArray[i][j] != 9) {
                    if (i > 0 && j > 0) {
                        if (mineArray[i - 1][j - 1] == 9) {
                            boxcount++;
                        }
                    }

                    if (i > 0) {
                        if (mineArray[i - 1][j] == 9) {
                            boxcount++;
                        }
                    }

                    if (i > 0 && j < btnhgt - 1) {
                        if (mineArray[i - 1][j + 1] == 9) {
                            boxcount++;
                        }
                    }

                    if (i < btnwdth - 1 && j > 0) {
                        if (mineArray[i + 1][j - 1] == 9) {
                            boxcount++;
                        }
                    }
                    if (i < btnwdth - 1) {
                        if (mineArray[i + 1][j] == 9) {
                            boxcount++;
                        }
                    }

                    if (i < btnwdth - 1 && j < btnhgt - 1) {
                        if (mineArray[i + 1][j + 1] == 9) {
                            boxcount++;
                        }
                    }

                    if (j > 0) {
                        if (mineArray[i][j - 1] == 9) {
                            boxcount++;
                        }
                    }
                    if (j < btnhgt - 1) {
                        if (mineArray[i][j + 1] == 9) {
                            boxcount++;
                        }
                    }
                    mineArray[i][j] = boxcount;
                }
            }
        }

        for (int i = 0; i < btnwdth; i++) {
            for (int j = 0; j < btnhgt; j++) {
                System.out.print(" " + mineArray[i][j]);
            }
            System.out.println("");
        }

    }

    public int[] getRndmNos(int btnwdth, int btnhgt, int mines) {
        Random rand = new Random();
        int rndmines[] = new int[btnwdth * btnhgt];
        boolean in = false;
        int count = 0;
        while (count < mines) {
            int rndno = (int) ((btnwdth * btnhgt) * (rand.nextDouble())) + 1;
            in = false;
            for (int i = 0; i < count; i++) {
                if (rndmines[i] == rndno) {
                    in = true;
                    break;
                }
            }
            if (!in) {
                rndmines[count++] = rndno;
            }
        }
        return rndmines;
    }

    public void actionPerformed(ActionEvent ae) {

        if (ae.getSource() == smiley) {
            reset();
        } else {
            for (int i = 0; i < btnwdth; i++) {
                for (int j = 0; j < btnhgt; j++) {
                    if (button[i][j] == ae.getSource()) {
                        if (mineArray[i][j] == 9) {
                            for (int k = 0; k < btnwdth; k++) {
                                for (int l = 0; l < btnhgt; l++) {

                                    if (mineArray[k][l] == 9) {
                                        button[k][l].setIcon(pitImageIcon);
                                    }

                                    button[k][l].removeActionListener(this);
                                    button[k][l].removeMouseListener(this);

                                }
                            }

                        }
                        if (mineArray[i][j] == 1) {
                            button[i][j].setIcon(oneImageIcon);
                        }
                        if (mineArray[i][j] == 2) {
                            button[i][j].setIcon(twoImageIcon);
                        }
                        if (mineArray[i][j] == 3) {
                            button[i][j].setIcon(threeImageIcon);
                        }
                        if (mineArray[i][j] == 4) {
                            button[i][j].setIcon(fourImageIcon);
                        }
                        if (mineArray[i][j] == 5) {
                            button[i][j].setIcon(fiveImageIcon);
                        }
                        if (mineArray[i][j] == 0) {
                            findAllEmpty(i, j);
                        }
                    }
                }
            }
        }
    }

    public void findAllEmpty(int boxX, int boxY) {
        int arrX[] = new int[(btnwdth) * (btnhgt)];
        int arrY[] = new int[(btnwdth) * (btnhgt)];
        int cntEmpty = 0;
        for (int i = 0; i < ((btnwdth) * (btnhgt)); i++) {
            arrX[i] = -1;
            arrY[i] = -1;
        }
        arrX[cntEmpty] = boxX;
        arrY[cntEmpty] = boxY;
        cntEmpty++;

        for (int i = 0; i < cntEmpty; i++) {
            if (arrX[i] > 0) {
                int xxX = arrX[i] - 1;
                int yyY = arrY[i];
                if (mineArray[xxX][yyY] == 0) {
                    if (!findIn(arrX, arrY, cntEmpty, xxX, yyY)) {
                        arrX[cntEmpty] = xxX;
                        arrY[cntEmpty] = yyY;
                        cntEmpty++;
                    }
                }
            }

            if (arrX[i] < (btnwdth - 1)) {
                int xxX = arrX[i] + 1;
                int yyY = arrY[i];
                if (mineArray[xxX][yyY] == 0) {
                    if (!findIn(arrX, arrY, cntEmpty, xxX, yyY)) {
                        arrX[cntEmpty] = xxX;
                        arrY[cntEmpty] = yyY;
                        cntEmpty++;
                    }
                }
            }

            if (arrY[i] > 0) {
                int xxX = arrX[i];
                int yyY = arrY[i] - 1;
                if (mineArray[xxX][yyY] == 0) {
                    if (!findIn(arrX, arrY, cntEmpty, xxX, yyY)) {
                        arrX[cntEmpty] = xxX;
                        arrY[cntEmpty] = yyY;
                        cntEmpty++;
                    }
                }
            }

            if (arrY[i] < (btnhgt - 1)) {
                int xxX = arrX[i];
                int yyY = arrY[i] + 1;
                if (mineArray[xxX][yyY] == 0) {
                    if (!findIn(arrX, arrY, cntEmpty, xxX, yyY)) {
                        arrX[cntEmpty] = xxX;
                        arrY[cntEmpty] = yyY;
                        cntEmpty++;
                    }
                }
            }
        }

        for (int k = 0; k < cntEmpty; k++) {
            button[arrX[k]][arrY[k]].setBackground(new Color(200, 200, 250));
        }

    }

    public boolean findIn(int[] arrX, int[] arrY, int cntEmpty, int xxX, int yyY) {
        int j = 0;
        for (j = 0; j < cntEmpty; j++) {
            if ((arrX[j] == (xxX)) && (arrY[j] == (yyY))) {
                return true;
            }
        }
        return false;
    }

    public void mouseClicked(MouseEvent arg0) {
        // TODO Auto-generated method stub
    }

    public void mousePressed(MouseEvent me) {
        for (int i = 0; i < btnwdth; i++) {
            for (int j = 0; j < btnhgt; j++) {
                if (button[i][j] == me.getSource()) {
                    smiley.setIcon(cryImageIcon);
                }
            }
        }

        if (me.getSource() == smiley) {
            smiley.setIcon(cryImageIcon);
        }
    }

    public void mouseReleased(MouseEvent me) {
        // TODO Auto-generated method stub
        if (me.getSource() == smiley) {
            smiley.setIcon(smileyImageIcon);
        }
        for (int i = 0; i < btnwdth; i++) {
            for (int j = 0; j < btnhgt; j++) {
                if (button[i][j] == me.getSource()) {
                    if (mineArray[i][j] == 9) {
                        smiley.setIcon(lossImageIcon);
                        int a = JOptionPane.showConfirmDialog(screen, "Game Over. \n You scored " + 
                                score+ " Points"+ "\nDo you want to try again?",
                                "Game Over",JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE);
                        if (a == JOptionPane.YES_OPTION) {
                            reset();
                        } else if (a == JOptionPane.NO_OPTION) {
                            System.exit(0);
                        }
                    } else {
                        smiley.setIcon(smileyImageIcon);
                        score = Integer.valueOf(points.getText()) + 1;
                        points.setText(Integer.toString(score));
                    }
                }

            }
        }

    }

    public ImageIcon getScaledImage(String imageString) {
        ImageIcon imageIcon = new ImageIcon(imageString);
        Image image = imageIcon.getImage();
        Image newimg = image.getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(newimg);
        return imageIcon;
    }

    public void mouseEntered(MouseEvent arg0) {
        // TODO Auto-generated method stub

    }

    public void mouseExited(MouseEvent arg0) {
        // TODO Auto-generated method stub

    }
}
