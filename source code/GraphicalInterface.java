
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

import java.io.File;

import java.util.List;
import java.util.ArrayList;

/**
 * Provides the GUI of the Cinema Booking System.
 * Different buttons provide access to the data in the system.
 * It builds and displays the application GUI and
 * initialises all other components.
 *
 * 
 * @author Fariha Nazmul
 * @version 01.11.10
 */
public class GraphicalInterface implements Interface {
    // static fields:
    private final static String newline = "\n";
    private static final String VERSION = "Version 2.1";
    private static JFileChooser fileChooser =
            new JFileChooser(System.getProperty("user.dir"));

    // The cinema booking system to be viewed and manipulated.
    private CinemaSystemEngine cinemaSystem;
    // A file handler for the IO operations
    private FileHandler fileHandler;

    // fields for swing components:
    private JFrame frame;
    private JLabel modeLabel;
    private JLabel versionLabel;
    private JRadioButton modeAdmin;
    private JRadioButton modeCustom;
    private ButtonGroup modeButtons;    
    private JTextArea outputTextArea;
    private JButton addcinema;
    private JButton addfilm;
    private JButton addFrom;
    private JButton load;
    private JButton save;
    private JButton when;
    private JButton where;
    private JButton book;
    private JButton show;
    private JButton showAll;
    private JButton exit;
    private JMenuItem loadItem;
    private JMenuItem saveItem;
    private JMenuItem addFromItem;
    private JMenuItem exitItem;
    private DefaultListModel filmListModel;
    private DefaultListModel cinemaListModel;
    
    /**
     * Creates an GraphicalInterface.
     */
    public GraphicalInterface(CinemaSystemEngine cinemaSystem) {
        this.cinemaSystem = cinemaSystem;
        this.fileHandler = new FileHandler(this.cinemaSystem);

        filmListModel = new DefaultListModel();        
        cinemaListModel = new DefaultListModel();
        createFilmListModel();
        createCinemaListModel();

        makeFrame();
    }

    /**
     * Displays the GUI on screen.
     */
    public void run(){
        frame.setVisible(true);
    }

    // menu and button functions

   /**
   * AddCinema function: adds cinema in the application.
   */
    public void addCinema(){  
        String[] dialogLabels = {"Cinema Hall Name:\n", "Number of Rows:\n",
                                 "Number of Columns:\n"};
        runDialogBox(CommandWord.ADD_CINEMA, dialogLabels);
        createCinemaListModel();
        frame.pack();
    }
   
   /**
   * AddFilm function: adds films in the application.
   */
    public void addFilm(){
        String[] dialogLabels = {"Film Name:\n", "Cinema Hall name:\n",
                                 "Days (Separated by Comma):\n"};
        runDialogBox(CommandWord.ADD_FILM, dialogLabels);
        createFilmListModel();
        frame.pack();
    }

   /**
   * Book function: books seats for a film show.
   */
    public void bookSeat(){
        String[] dialogLabels = {"Person Name:\n", "Film Name:\n",
                                 "Cinema Name:\n", "Show Day:\n",
                                 "Number of Seats:\n",};
        runDialogBox(CommandWord.BOOK, dialogLabels);
        frame.pack();
    }

   /**
    * Where function: finds the cinema hall where a film is showing.
    */
    public void whereFilm(){
        String[] dialogLabels = {"Film name:\n","Day:\n"};
        runDialogBox(CommandWord.WHERE, dialogLabels);
        frame.pack();
    }   

   /**
   * When function: finds when a film is showing.
   */
    public void whenFilm(){
        String field = "Film name";
        runOptionPane(field, CommandWord.WHEN);
    }    

    /**
    * Show function: shows the booking for the booking number.
    */
    public void showBooking(){
        String field = "Booking Number";
        runOptionPane(field, CommandWord.SHOW);
    }

     /**
     * ShowAll function: shows all the bookings made by a person.
     */
    public void showAllBooking(){        
        String field = "Person's name";
        runOptionPane(field, CommandWord.SHOW_ALL);        
    }
    
    /**
     * AddFrom function: opens a file chooser to select a file,
     * and then reads and executes commands from the chosen file.
     */
    public void addFromFile() {
        runFileChooser(newline, CommandWord.ADD_FROM);
        createCinemaListModel();
        createFilmListModel();
        frame.pack();
    }

    /**
     * Load function: opens a file chooser to select a file,
     * and then loads the system from the chosen file.
     */
    public void loadFile() {
        runFileChooser(newline, CommandWord.LOAD);
        createCinemaListModel();
        createFilmListModel();
        frame.pack();
    }

    /**
     * Save function: saves the current system to a file.
     */
    public void saveFile() {
        runFileChooser(newline, CommandWord.SAVE);
        frame.pack();
    }
    
    /**
     * Mode function: changes the user mode of the application.
     */
    public void switchMode(User user){
        String status = "";
        ArrayList<String> commandParameter = new ArrayList<String>();
        commandParameter.add(user.toString());
        Command command = new Command(CommandWord.MODE, commandParameter);
        try{
            status = cinemaSystem.setMode(command);
        } catch(Exception ex){
            status = ex.getMessage();
        }
        clearStatus();
        showStatus(status + newline);
        frame.pack();
    }

    /**
     * Exit function: quits the application.
     */
    public void exit() {
        System.exit(0);
    }

    /**
     * 'About' function: shows the 'about' box.
     */
    public void showAbout() {
        JOptionPane.showMessageDialog(frame,
                "Cinema Booking System\n" + VERSION,
                "About Cinema Booking System",
                JOptionPane.INFORMATION_MESSAGE);
    }
 
    // support methods

    /**
     * Given a command and dialogLabels, creates a dialog box
     * to get user input and then executes the command with input.
     * Used for commands that need multiple user inputs.
     */
    public void runDialogBox(CommandWord cmd, String[] dialogLabels){
        String status = null;
        ArrayList<String> commandParameter =
                 new DialogBox(frame, cmd, dialogLabels).getInput();
        if(commandParameter.contains("") || commandParameter.isEmpty()) return;
        Command command = new Command(cmd, commandParameter);
        try{
            switch(cmd){
                case ADD_CINEMA: status = cinemaSystem.addCinema(command);break;
                case ADD_FILM: status = cinemaSystem.addFilm(command);break;
                case BOOK: status = cinemaSystem.book(command);break;
                case WHERE: status = cinemaSystem.where(command);break;
            }

        }catch(Exception ex){
            status = ex.getMessage();
        }
        showStatus(status+newline);
    }

    /**
     * Given a field name and command, creates a JOptionPane
     * to get user input and then executes the command with input.
     * Used for commands that need single user input.
     */
    public void runOptionPane(String field, CommandWord cmd){
        String input = (String)JOptionPane.showInputDialog(
                                        frame,
                                        "Please Enter the " + field + ":\n",
                                        cmd.toString().toUpperCase(),
                                        JOptionPane.PLAIN_MESSAGE,
                                        null, null, "");
        if ((input == null) || (input.length() == 0)) {
                        return;}
        ArrayList<String> commandParameter = new ArrayList<String>();
        commandParameter.add(input.trim());
        Command command = new Command(cmd, commandParameter);
        switch(cmd){
            case WHEN: showStatus(cinemaSystem.when(command)+newline);break;
            case SHOW: showStatus(cinemaSystem.show(command)+newline);break;
            case SHOW_ALL: showStatus(cinemaSystem.showAll(command)+newline);
                            break;
        }
    }

     /**
     * Given a msg and a command, creates a FileChooser
     * to get user specified file name and then executes the command
     * with input.
     * Used for commands that need file operation.
     */
    public void runFileChooser(String msg, CommandWord cmd){
        int returnVal = 1;
        String status = null;
        switch(cmd){
            case ADD_FROM: returnVal = fileChooser.showOpenDialog(frame);break;
            case LOAD: returnVal = fileChooser.showOpenDialog(frame);break;
            case SAVE: returnVal = fileChooser.showSaveDialog(frame);break;
        }
        if (returnVal != JFileChooser.APPROVE_OPTION) {
            return;  // cancelled
        }
        File selectedFile = fileChooser.getSelectedFile();
        if(selectedFile.getName()!= null){
            ArrayList<String> commandParameter = new ArrayList<String>();
            commandParameter.add(selectedFile.getName());
            Command command = new Command(cmd, commandParameter);
            try {
                switch(cmd){
                    case ADD_FROM: status = fileHandler.addFrom(command);;break;
                    case LOAD: this.cinemaSystem = fileHandler.load(command);
                                status = "File Load Successful";break;
                    case SAVE: status = fileHandler.save(command);;break;
                    }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame,
                        ex.getMessage() +newline + cmd.toString().toUpperCase()
                        + " File Operation Not Successful.",
                        cmd.toString().toUpperCase()+"File Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
        showStatus(status+ newline);
    }
    
    /**
     * Shows a message in the middle pane.
     * @param text The status message.
     */
   public void showStatus(String text) {
        outputTextArea.append(text + newline);
        outputTextArea.setCaretPosition(
                outputTextArea.getDocument().getLength());
    }

    /**
     * Clears the text area.
     */
    public void clearStatus() {
        outputTextArea.setText("");
        outputTextArea.append("Welcome to Cinema Booking System."+ newline);
        outputTextArea.append("Click on any button on the left." 
                + newline+ newline);
        outputTextArea.setCaretPosition(
                outputTextArea.getDocument().getLength());
    }

    /**
     * Enables or disables toolbar buttons and menu items for ADMIN mode.
     * @param status  'true' to enable, 'false' to disable.
     */
    public void setAdminButtonsVisible(boolean status) {
        
        addcinema.setVisible(status);
        addfilm.setVisible(status);
        addFrom.setVisible(status);
        load.setVisible(status);
        save.setVisible(status);
        exit.setEnabled(status);

        loadItem.setEnabled(status);
        saveItem.setEnabled(status);
        addFromItem.setEnabled(status);
        exitItem.setEnabled(status);
        //commandLineItem.setEnabled(status);
    }

    /**
     * Enables or disables toolbar buttons and menu items for CUSTOM mode.
     * @param status  'true' to enable, 'false' to disable.
     */
    public void setCustomButtonsVisible(boolean status) {
        book.setVisible(status);
    }

    /**
     * Creates a list model with all the existing film names.
     */
    public void createFilmListModel() {
        List<String> filmList = cinemaSystem.getAllFilms();
        filmListModel.clear();
        for (String filmName : filmList) {
            filmListModel.addElement(filmName);
        }
    }

    /**
     * Creates a list model with all the existing cinema names.
     */
    public void createCinemaListModel() {
        List<String> cinemaList = cinemaSystem.getAllCinemas();
        cinemaListModel.clear();
        for (String cinemaName : cinemaList) {
            cinemaListModel.addElement(cinemaName);
        }
    }

    // Swing stuff to build the frame and all its components and menus

    /**
     * Creates the Swing frame and its content.
     */
    public void makeFrame() {
        // Create the fram
        frame = new JFrame("Cinema Booking System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel contentPane = (JPanel) frame.getContentPane();
        contentPane.setBorder(new EmptyBorder(6, 6, 6, 6));
        // Specify the layout manager with spacing
        contentPane.setLayout(new BorderLayout(6, 6));

        makeMenuBar();

        makeWestPane();
        makeEastPane();
        makeNorthPane();
        makeSouthPane();
        makeCenterPane();

        // building is done - arrange the components
        if (cinemaSystem.getMode() == User.USER) {
            setAdminButtonsVisible(false);
            setCustomButtonsVisible(false);
        } else if (cinemaSystem.getMode() == User.ADMIN) {
            setAdminButtonsVisible(true);
            setCustomButtonsVisible(false);
        } else if (cinemaSystem.getMode() == User.CUSTOM) {
            setAdminButtonsVisible(false);
            setCustomButtonsVisible(true);
        }

        frame.pack();
        // place the frame at the center of the screen and show
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(d.width / 2 - frame.getWidth() / 2,
                d.height / 2 - frame.getHeight() / 2);
        //frame.setVisible(true);
    }

    /**
     * Creates the main frame's menu bar.
     */
    public void makeMenuBar() {
        final int SHORTCUT_MASK =
                Toolkit.getDefaultToolkit().getMenuShortcutKeyMask();

        JMenuBar menubar = new JMenuBar();
        frame.setJMenuBar(menubar);
        JMenu menu;

        // create the File menu
        menu = new JMenu("File");
        menubar.add(menu);

        addFromItem = new JMenuItem("AddFrom");
        addFromItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_A, SHORTCUT_MASK));
        addFromItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { addFromFile(); }
            });
        menu.add(addFromItem);
        menu.addSeparator();

        loadItem = new JMenuItem("Load");
        loadItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_L, SHORTCUT_MASK));
        loadItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                loadFile();
            }
        });
        menu.add(loadItem);

        saveItem = new JMenuItem("Save");
        saveItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_S, SHORTCUT_MASK));
        saveItem.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                saveFile();
            }
        });
        menu.add(saveItem);
        menu.addSeparator();

        exitItem = new JMenuItem("Exit");
        exitItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_Q, SHORTCUT_MASK));
        exitItem.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                exit();
            }
        });
        menu.add(exitItem);

        // create the Help menu
        menu = new JMenu("Help");
        menubar.add(menu);
        JMenuItem item = new JMenuItem("About CinemaBookingSystem...");
        item.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                showAbout();
            }
        });
        menu.add(item);

    }

    /**
     * Creates the main frame's North Panel.
     */
    public void makeNorthPane() {

        // Create the mode selection buttons on the NORTH
        modeLabel = new JLabel("Mode ");
        modeAdmin = new JRadioButton("ADMIN");
        modeAdmin.setToolTipText("Change to ADMIN mode");
        modeCustom = new JRadioButton("CUSTOM");
        modeCustom.setToolTipText("Change to CUSTOM mode");

        // add the radio buttons to a button group
        modeButtons = new ButtonGroup();
        modeButtons.add(modeAdmin);
        modeButtons.add(modeCustom);

        // add action listener to the radio buttons
        modeAdmin.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                setAdminButtonsVisible(true);
                setCustomButtonsVisible(false);
                switchMode(User.ADMIN);
            }
        });
        modeCustom.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                setAdminButtonsVisible(false);
                setCustomButtonsVisible(true);
                switchMode(User.CUSTOM);
            }
        });

        // Add mode buttons into panel with flow layout for spacing
        JPanel flowMode = new JPanel();
        flowMode.setLayout(new FlowLayout(FlowLayout.CENTER, 35, 0));
        flowMode.setBorder(
                BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
        flowMode.add(modeLabel);
        flowMode.add(modeAdmin);
        flowMode.add(modeCustom);
        JPanel contentPane = (JPanel) frame.getContentPane();
        contentPane.add(flowMode, BorderLayout.NORTH);
    }

    /**
     * Creates the main frame's South Panel.
     */
    public void makeSouthPane() {
        // Create the version label at the bottom
        versionLabel = new JLabel(VERSION);
        JPanel contentPane = (JPanel) frame.getContentPane();
        contentPane.add(versionLabel, BorderLayout.SOUTH);
    }

    /**
     * Creates the main frame's Center Panel.
     */
    public void makeCenterPane() {

        // Create the output panel in the CENTER
        JPanel middlePane = new JPanel();
        middlePane.setLayout(new BoxLayout(middlePane, BoxLayout.Y_AXIS));
        middlePane.setBorder(
                BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
        
        outputTextArea = new JTextArea(12, 30);
        outputTextArea.setToolTipText("Output Terminal");
        outputTextArea.setBorder(BorderFactory.createLineBorder(Color.black));
        outputTextArea.setEditable(false);
        outputTextArea.setFont(new Font("Serif", Font.ITALIC, 14));
        outputTextArea.setLineWrap(true);
        outputTextArea.setWrapStyleWord(true);
        clearStatus();
        JScrollPane outputScrollPane = new JScrollPane(outputTextArea);
        outputScrollPane.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        outputScrollPane.setBorder(
                BorderFactory.createEmptyBorder(10, 10, 10, 10));
        middlePane.add(outputScrollPane);

        JPanel contentPane = (JPanel) frame.getContentPane();
        contentPane.add(middlePane, BorderLayout.CENTER);
    }

    /**
     * Creates the main frame's West Panel.
     */
    public void makeWestPane() {

        // Create the toolbar with the buttons
        JPanel toolbar = new JPanel();
        toolbar.setLayout(new GridLayout(0, 1, 0, 10));

        addcinema = new JButton("Add Cinema");
        addcinema.setToolTipText("Click to add a cinema");
        addcinema.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { addCinema();}
            });
        toolbar.add(addcinema);

        addfilm = new JButton("Add Film");
        addfilm.setToolTipText("Click to add a film");
        addfilm.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { addFilm(); }
            });
        toolbar.add(addfilm);

        addFrom = new JButton("Add From");
        addFrom.setToolTipText("Click to add from a file");
        addFrom.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { addFromFile(); }
            });
        toolbar.add(addFrom);        

        when = new JButton("When");
        when.setToolTipText("Click to find a show day");
        when.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { whenFilm(); }
            });
        toolbar.add(when);

        where = new JButton("Where");
        where.setToolTipText("Click to find a show hall");
        where.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { whereFilm(); }
            });
        toolbar.add(where);

        show = new JButton("Show");
        show.setToolTipText("Click to find a booking");
        show.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { showBooking(); }
            });
        toolbar.add(show);

        showAll = new JButton("Show All");
        showAll.setToolTipText("Click to find all booking");
        showAll.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { showAllBooking(); }
            });
        toolbar.add(showAll);


        book = new JButton("Book");
        book.setToolTipText("Click to book seats");
        book.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { bookSeat(); }
            });
        toolbar.add(book);

        load = new JButton("Load");
        load.setToolTipText("Click to load a system");
        load.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { loadFile(); }
            });
        toolbar.add(load);

        save = new JButton("Save");
        save.setToolTipText("Click to save the system");
        save.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { saveFile(); }
            });
        toolbar.add(save);

        // Add toolbar into panel with flow layout for spacing
        JPanel flow = new JPanel();
        flow.setLayout(new FlowLayout(FlowLayout.CENTER));
        flow.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
        flow.add(toolbar);

        JPanel contentPane = (JPanel) frame.getContentPane();
        contentPane.add(flow, BorderLayout.WEST);
    }

    /**
     * Creates the main frame's East Panel.
     */
    public void makeEastPane() {

        // Create the east pane with the lists and exit button on EAST
        JPanel eastPanel = new JPanel();
        eastPanel.setLayout(new BoxLayout(eastPanel, BoxLayout.Y_AXIS));

        // Create the list pane with the lists
        JPanel listPanel = new JPanel();
        listPanel.setLayout(new GridLayout(0, 1, 0, 10));
        listPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 20, 10));

        // Panel for the film list
        JPanel filmListPanel = new JPanel();
        filmListPanel.setLayout(new BoxLayout(filmListPanel, BoxLayout.Y_AXIS));
        filmListPanel.setBorder(
                BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
        JLabel filmLabel = new JLabel("Films Showing");
        filmListPanel.add(filmLabel);

        JList allFilms = new JList(filmListModel);
        allFilms.setBorder(BorderFactory.createLineBorder(Color.black));
        allFilms.setLayoutOrientation(JList.VERTICAL);
        allFilms.setVisibleRowCount(5);
        JScrollPane filmListScroller = new JScrollPane(allFilms);
        filmListScroller.setPreferredSize(new Dimension(180, 130));
        filmListScroller.setBorder(
                BorderFactory.createEmptyBorder(10, 10, 20, 10));
        filmListPanel.add(filmListScroller);
        listPanel.add(filmListPanel);

        // Panel for the cinema list
        JPanel cinemaListPanel = new JPanel();
        cinemaListPanel.setLayout(
                new BoxLayout(cinemaListPanel, BoxLayout.Y_AXIS));
        cinemaListPanel.setBorder(
                BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
        JLabel cinemaLabel = new JLabel("Cinema Halls");
        cinemaListPanel.add(cinemaLabel);
        JList allCinemas = new JList(cinemaListModel);
        allCinemas.setBorder(BorderFactory.createLineBorder(Color.black));
        allCinemas.setLayoutOrientation(JList.VERTICAL);
        allCinemas.setVisibleRowCount(5);
        JScrollPane cinemaListScroller = new JScrollPane(allCinemas);
        cinemaListScroller.setPreferredSize(new Dimension(180, 130));
        cinemaListScroller.setBorder(
                BorderFactory.createEmptyBorder(10, 10, 20, 10));
        cinemaListPanel.add(cinemaListScroller);
        listPanel.add(cinemaListPanel);

        eastPanel.add(listPanel);

        // exit button
        exit = new JButton("Exit");
        exit.setToolTipText("Click to EXIT");
        exit.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                exit();
            }
        });
        JPanel flowExit = new JPanel();
        flowExit.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        flowExit.add(exit);

        eastPanel.add(flowExit);

        // Add to contentpane
        JPanel contentPane = (JPanel) frame.getContentPane();
        contentPane.add(eastPanel, BorderLayout.EAST);
    }
}
