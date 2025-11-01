//Joey Barton
//Holds the functionality of the Simulator and GUI

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

public class sceneController {

    //Set's the default number of hikers.
    private int numOfHikers = 156; 
    
    //Set's the number of hiking groups (stacks) will be allowed on the mountain every 30 minutes
    //We start with two when the trails open for the day
    private int numOfGroupsOnMountain = 2;

    //Three queues, three trails up the mountain
    private Queue entranceEastPath = new Queue();
    private Queue entranceWestPath = new Queue();
    private Queue oldPathEntrance = new Queue();

    //Load all hikers into an arrayList while they are on the mountain
    private ArrayList<Hiker> onTheMountain = new ArrayList<>();

    //Counters for each entrance
    private int EastPathAttendance = 0;
    private int WestPathAttendance = 0;
    private int OldPathAttendance = 0;

    //People who begin climbing first will have a slight speed advantage
    private int earlyBirdSpeedBoost = 2;

    @FXML
    private Button start_Button;

    @FXML
    private Button openTrail_Button;

    @FXML
    private Button closeTrail_Button;

    @FXML
    private AnchorPane main_pane;

    @FXML
    private TextArea hikerInfo_TextArea;
    
    @FXML
    private Label title_label;

    @FXML
    private TextField numOfHikers_TextField;
    
    @FXML
    private Label mountain_manager_label;

    @FXML
    private CheckBox manager_CheckBox;

    @FXML
    private TextArea clock_TextArea;


    @FXML
    public void initialize() {
        //Provides initial instructions and description of Simulator
        hikerInfo_TextArea.setWrapText(true);
        hikerInfo_TextArea.setText(getWelcomeText());
    }

    @FXML
    private void start_Button_clicked(ActionEvent e){
        //create hikers
        //print info to hikerInfo_TextArea
        //put hikers into groups (stacks)
        //put groups into queues

        //We want the .txt files to start empty
        NoteManager freshStart = new NoteManager();
        try {
            freshStart.clearNotes("HikersOnMountain.txt");
            freshStart.clearNotes("HikersReturned.txt");
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        //This code block allows the user to input the number of hikers for their simulation
        //Starts with the default number of hikers if user doesn't provide input
        String userInput = numOfHikers_TextField.getText();
        int inputHikers = numOfHikers;
        try {
            inputHikers = Integer.parseInt(userInput);
        } catch (NumberFormatException n) {
            System.out.println("Invalid input: not a number");
        }
        numOfHikers = inputHikers;

        //Creates all the hikers arriving to the mountain.
        Hikers allHikers = new Hikers(numOfHikers);
        ArrayList<Hiker> h = allHikers.getAllHikers();

        //Writes hikers info to the TextArea
        hikerInfo_TextArea.setText(allHikers.toString());

        //Holds the number of groups (potentially 1 group short, if not divisible by 10 (ex: 157 would need 16 groups))
        int numOfGroups = h.size() / 10;


        //If the user has selected the Mountain Manager Mode, the program will determine which hiking trail has the fewest groups in the queue
        //and choose that path for the following group.
        if(manager_CheckBox.isSelected()) {
   
            //Creates a new stack for every 10 people
            //Pushes hikers to the stack till it is full (capacity is 10)
            //Randomly sends a stack, once it is full, to a queue
            for(int i = 0; i < numOfGroups; i++) {
                Stack newStack = new Stack(i);
                for(int j = 0; j < 10; j++) {
                    newStack.push(h.get((i*10) + j));
                }
  
                if(entranceEastPath.getLength() <= entranceWestPath.getLength() && entranceEastPath.getLength() <= oldPathEntrance.getLength()) {
                    newStack.setTrailPrefix("East Path");
                    entranceEastPath.enqueue(newStack);
                } else if(entranceWestPath.getLength() <= entranceEastPath.getLength() && entranceWestPath.getLength() <= oldPathEntrance.getLength()) {
                    newStack.setTrailPrefix("West Path");
                    entranceWestPath.enqueue(newStack);
                } else {
                    newStack.setTrailPrefix("Old Path");
                    oldPathEntrance.enqueue(newStack);
                }

            }

            //If there is any remaining hikers, they will be put into this final stack and sent to the back of a random queue
            if((h.size() % 10) != 0) {
                int finalHikers = (h.size() % 10);
                Stack finalStack = new Stack(numOfGroups, finalHikers);

                for(int j = 0; j < finalHikers; j++) {
                    finalStack.push(h.get(((numOfGroups) * 10) + j));
                } 

                if(entranceEastPath.getLength() <= entranceWestPath.getLength() && entranceEastPath.getLength() <= oldPathEntrance.getLength()) {
                    finalStack.setTrailPrefix("East Path");
                    entranceEastPath.enqueue(finalStack);
                } else if(entranceWestPath.getLength() <= entranceEastPath.getLength() && entranceWestPath.getLength() <= oldPathEntrance.getLength()) {
                    finalStack.setTrailPrefix("West Path");
                    entranceWestPath.enqueue(finalStack);
                } else {
                    finalStack.setTrailPrefix("Old Path");
                    oldPathEntrance.enqueue(finalStack);
                }
            }
        } else {
            //Randomizer
            Random rand = new Random();
            
            //Creates a new stack for every 10 people
            //Pushes hikers to the stack till it is full (capacity is 10)
            //Randomly sends a stack, once it is full, to a queue
            for(int i = 0; i < numOfGroups; i++) {
                Stack newStack = new Stack(i);
                for(int j = 0; j < 10; j++) {
                    newStack.push(h.get((i*10) + j));
                }

                int randomNum = rand.nextInt(3);
                if(randomNum == 0) {
                    newStack.setTrailPrefix("East Path");
                    entranceEastPath.enqueue(newStack);
                } else if(randomNum == 1) {
                    newStack.setTrailPrefix("West Path");
                    entranceWestPath.enqueue(newStack);
                } else {
                    newStack.setTrailPrefix("Old Path");
                    oldPathEntrance.enqueue(newStack);
                }

            }

            //If there is any remaining hikers, they will be put into this final stack and sent to the back of a random queue
            if((h.size() % 10) != 0) {
                int finalHikers = (h.size() % 10);
                Stack finalStack = new Stack(numOfGroups, finalHikers);

                for(int j = 0; j < finalHikers; j++) {
                    finalStack.push(h.get(((numOfGroups) * 10) + j));
                } 

                int randomNum = rand.nextInt(3);
                if(randomNum == 0) {
                    finalStack.setTrailPrefix("East Path");
                    entranceEastPath.enqueue(finalStack);
                } else if(randomNum == 1) {
                    finalStack.setTrailPrefix("West Path");
                    entranceWestPath.enqueue(finalStack);
                } else {
                    finalStack.setTrailPrefix("Old Path");
                    oldPathEntrance.enqueue(finalStack);
                }
            }
        }

        
    }

    @FXML
    private void openTrail_Button_clicked(ActionEvent p) {
        //Let hikers in from queues at each entrance
        //Write hikers names to file
        hikerInfo_TextArea.setText("");

        //Disables the TextField once the program has started
        numOfHikers_TextField.setEditable(false);

        clock_TextArea.setText("7:00 AM");

        //We want to take groups (stacks) from each path (queue)
        //We check if any queue is empty first
        //Random creates uneven queue lengths
        for(int i = 0; i < numOfGroupsOnMountain; i++) {
            if(entranceEastPath.isEmpty() != true) {
                Stack EastGroup = entranceEastPath.dequeue();
                unloadGroup(EastGroup, "East");
            }
            if(entranceWestPath.isEmpty() != true) {
                Stack WestGroup = entranceWestPath.dequeue();
                unloadGroup(WestGroup, "West");
            }
            if(oldPathEntrance.isEmpty() != true) {
                Stack OldGroup = oldPathEntrance.dequeue();
                unloadGroup(OldGroup, "Old");
            }

            //We want to decrement the speed boost for each hiking group past the first
            //But we don't want it to go past zero
            earlyBirdSpeedBoost = Math.max(0, earlyBirdSpeedBoost - 1);
        }

        //We pause the program for 3 seconds to simulate the passage of 30 minutes on the mountain.
        PauseTransition pause1 = new PauseTransition(Duration.seconds(3));
        pause1.setOnFinished(e -> {
            clock_TextArea.setText("7:30 AM");

            for(int i = 0; i < numOfGroupsOnMountain; i++) {
                if(entranceEastPath.isEmpty() != true) {
                    Stack EastGroup = entranceEastPath.dequeue();
                    unloadGroup(EastGroup, "East");
                }
                if(entranceWestPath.isEmpty() != true) {
                    Stack WestGroup = entranceWestPath.dequeue();
                    unloadGroup(WestGroup, "West");
                }
                if(oldPathEntrance.isEmpty() != true) {
                    Stack OldGroup = oldPathEntrance.dequeue();
                    unloadGroup(OldGroup, "Old");
                }
            }

            PauseTransition pause2 = new PauseTransition(Duration.seconds(3));
            pause2.setOnFinished(e2 -> {
                clock_TextArea.setText("8:00 AM");

                for(int i = 0; i < numOfGroupsOnMountain; i++) {
                    if(entranceEastPath.isEmpty() != true) {
                        Stack EastGroup = entranceEastPath.dequeue();
                        unloadGroup(EastGroup, "East");
                    }
                    if(entranceWestPath.isEmpty() != true) {
                        Stack WestGroup = entranceWestPath.dequeue();
                        unloadGroup(WestGroup, "West");
                    }
                    if(oldPathEntrance.isEmpty() != true) {
                        Stack OldGroup = oldPathEntrance.dequeue();
                        unloadGroup(OldGroup, "Old");
                    }
                }

                PauseTransition pause3 = new PauseTransition(Duration.seconds(3));
                pause3.setOnFinished(e3 -> {
                    clock_TextArea.setText("8:30 AM");

                    for(int i = 0; i < numOfGroupsOnMountain; i++) {
                        if(entranceEastPath.isEmpty() != true) {
                            Stack EastGroup = entranceEastPath.dequeue();
                            unloadGroup(EastGroup, "East");
                        }
                        if(entranceWestPath.isEmpty() != true) {
                            Stack WestGroup = entranceWestPath.dequeue();
                            unloadGroup(WestGroup, "West");
                        }
                        if(oldPathEntrance.isEmpty() != true) {
                            Stack OldGroup = oldPathEntrance.dequeue();
                            unloadGroup(OldGroup, "Old");
                        }
                    }

                    PauseTransition pause4 = new PauseTransition(Duration.seconds(3)); 
                    pause4.setOnFinished(e4 -> {
                        clock_TextArea.setText("9:00 AM");
                    });
                    pause4.play();
                });
                pause3.play();
            });
            pause2.play();
        });
        pause1.play();
        
    }

    @FXML
    private void closeTrail_Button_clicked(ActionEvent p) {
        //Write names of hikers to another file
        //Print total number of hikers for each entrance on hikerInfo_TextArea
        hikerInfo_TextArea.setText("");
        hikerInfo_TextArea.appendText("East Path Attendance: " + EastPathAttendance + "\n");
        hikerInfo_TextArea.appendText("West Path Attendance: " + WestPathAttendance + "\n");
        hikerInfo_TextArea.appendText("Old Path Attendance: " + OldPathAttendance + "\n");

        NoteManager allNotes = new NoteManager();
        String filename = "HikersReturned.txt";
        int numOfHikers = onTheMountain.size();

        //We want to sort the hikers by speed
        //Faster hikers should complete the trail quicker
        insertionSortHikersSpeed();

        for(int i = 0; i < numOfHikers; i++) {
            Note returnedHiker = new Note(onTheMountain.get(i));
            allNotes.setNote(returnedHiker);
            try {
                allNotes.saveHikersReturnedNote(filename);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //Enables the TextField, allowing the user to start again.
        numOfHikers_TextField.setEditable(true);

    }

    //Helper method for providing welcome text
    public String getWelcomeText() {
        return """
            Welcome to the Purple Mountain Hiker Simulator 
        
            How to use:

            The default number of hikers is 156. Input any number of hikers BEFORE hitting the Start Button.
            You may enter the number of hikers in the 'Num of Hikers' text area.
        
            The Start Button prints the hikers information to the TextArea, loads the hikers into their groups (stack), and sends the full groups to one of three trails's queues, randomly.
            
            The Open Trail Button lets the hikers in from the queues at each trail entrance every 30 minutes and writes the hikers' names to a .txt file.
            The Simulation will simulate time, please wait until the clock hits 9:00AM before clicking the Close Trails Button!
            
            The Close Trails Button prints the number of hikers from each trail entrance and write the hikers' names to a .txt file, having returned safely.

            Selecting Mountain Manager Mode will change the simulation from sending each group of hikers to a random trail, to managing each group by sending them to the trail with the fewest hiking groups in the queue.
            """;
    }

    //Generic unload method, for all paths
    public void unloadGroup(Stack group, String pathName) {

        NoteManager allNotes = new NoteManager();
        String filename = "HikersOnMountain.txt";
        
        while(group.isEmpty() != true) {
            Note note = new Note(group.pop(), group);
            note.getHiker().setSpeed(earlyBirdSpeedBoost);
            onTheMountain.add(note.getHiker());
            allNotes.setNote(note);
            hikerInfo_TextArea.appendText(note.toString());

            try {
                allNotes.saveNote(filename);
            } catch (IOException e) {
                e.printStackTrace();
            }
            
            //Counter incrementer
            if(pathName.equals("East")) {
                EastPathAttendance++;
            } else if(pathName.equals("West")) {
                WestPathAttendance++;
            } else if(pathName.equals("Old")) {
                OldPathAttendance++;
            }
        }
    }

    //Insertion sort method
    //Takes the ArrayList of hikers that are on the mountain
    //Sorts hikers based on their speed attribute
    public ArrayList<Hiker> insertionSortHikersSpeed() {
        for(int i = 1; i < onTheMountain.size(); i++) {
            int j = i;
            while(j > 0 && onTheMountain.get(j).getSpeed() < onTheMountain.get(j - 1).getSpeed()) {
                Hiker temp = onTheMountain.get(j);
                onTheMountain.set(j, onTheMountain.get(j - 1));
                onTheMountain.set(j - 1, temp);
                j--;
            }
        }
        //Sorts from 0-5, but we want output 5-0
        //Faster speed means the hiker finishes first
        Collections.reverse(onTheMountain);

        return onTheMountain;
    }
    
}
