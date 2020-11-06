package com.arash;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import javax.swing.*;
import javax.swing.Timer;
import java.io.*;

//PhysicSimulation
public class PhysicSimulation extends JPanel implements ActionListener {
    Timer time = new Timer(2, this);
    public int force_x, force_y = 1;

    // List
    public PlanetList<Planet> planets;

    // Render Function
    public void inputRendered(String fileText){
        try{
            FileReader file = new FileReader("input.txt");
            BufferedReader buff = new BufferedReader(file);
            String str;
            int counter = 0;
            while ((str = buff.readLine()) != null){

                // first line of the file
                if(counter == 0){
                    if(str.equals("ArrayList")){
                        //this.planets = new ArrayList<Planet>();
                        this.planets = new ArrayListSimulation<Planet>(10);
                    } else if(str.equals("LinkedList")){
                        this.planets = new LinkedListSimulation<Planet>();
                    } else {
                        System.out.println("NOT VALID");
                    }
                }
                // export raw data
                if(this.planets != null){
                    ArrayList<String> temp = new ArrayList<>();
                    String[] temp2 = str.split(",");

                    for (int i = 0; i < temp2.length; i++) {
                        temp.add(temp2[i]);
                    }
                    // adding 3th line until end
                    if(counter > 1){
                        Planet planet = new Planet(temp2[0], Double.parseDouble(temp2[1]), Double.parseDouble(temp2[2]), Double.parseDouble(temp2[3]),
                                Double.parseDouble(temp2[4]), Double.parseDouble(temp2[5]), Double.parseDouble(temp2[6]));
                        // apply changes
                        this.planets.add(planet);
                    }
                    counter++;
                }
            }
        // Exception
        } catch (IOException e){
            System.out.println("FILE DID NOT FOUND!");
        }
        System.out.println("Planets in Render Fun: " + this.planets);
    }

    // Paint Component
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.setColor(Color.black);
        // index Mover
        int indexMover = 0;

        // for Planet class update
        inputRendered("input.txt");
        for (int i = 0; i < this.planets.size(); i ++){
            System.out.println("Planet pos X: " + this.planets.get(indexMover).initXF);
            System.out.println("Planet pos Y: " + this.planets.get(indexMover).initYF);
            System.out.println("Planet mass: " + this.planets.get(indexMover).mass);
            System.out.println("Planet size: " + this.planets.get(indexMover).size);
            g.fillOval((int)planets.get(indexMover).initXF + (int)planets.get(indexMover).initXV + force_x, (int)planets.get(indexMover).initYF + (int)planets.get(indexMover).initXY + force_y, (int)planets.get(indexMover).size, (int)planets.get(indexMover).size);
            indexMover++;
        }
        // animation
        time.start();
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        // force
        force_x += 1;
        force_y += 1;
        // render again - Delta Time
        repaint();
    }

    public static void main(String[] args) {
        PhysicSimulation Simulation = new PhysicSimulation();

        System.out.println(" -- Raw Text -- ");
        Simulation.inputRendered("input.txt");
        System.out.println(" -- Rendered -- ");
        int MAX_X = 768, MAX_Y = 768;
        JFrame jf = new JFrame();
        jf.setTitle("Physic Simulation");
        jf.setSize(MAX_X, MAX_Y);
        jf.add(Simulation);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setVisible(true);
    }
}
