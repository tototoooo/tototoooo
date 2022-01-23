import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.geometry.Point3D;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.PickResult;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Sphere;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Scale;
import javafx.scene.transform.Transform;
import javafx.stage.Stage;

import java.io.*;
import java.net.*;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Interface extends Application {



    double posX = 0;
    double posY = 0;
    double posXLattitude;
    double posYLongitude;
    double Lattitude;
    double Longitude;
    private Rotate ry = new Rotate(0, Rotate.Y_AXIS);
    private Point2D coordinates;
    HttpRequest requete;
    HttpResponse<String> response;
    //StringBuilder response;
    String inputLine;
    String reponse;
    /*JsonFlightFiller flightsFilled*/;

    @Override
    public void start(Stage primaryStage) throws Exception {
        World w = new World ("C:/Users/DIARIETOU/IdeaProjects/GLOBE/Resources/airport-codes_no_comma.csv");
        primaryStage.setTitle("Hello World !");
        Earth terre = new Earth();
        Group root = terre;
        Scene theScene = new Scene(root, 600, 400, true);
        primaryStage.setScene(theScene);
        primaryStage.show();

        PerspectiveCamera camera = new PerspectiveCamera(true);
        camera.setTranslateZ(-1000);
        camera.setNearClip(0.1);
        camera.setFarClip(2000.0);
        camera.setFieldOfView(35);
        theScene.setCamera(camera);

        theScene.addEventHandler(MouseEvent.ANY, event -> {
            if (event.getEventType() == MouseEvent.MOUSE_PRESSED) {
                //System.out.println("Clicked on : (" + event.getSceneX() + ", " + event.getSceneY() + ")");
                posX = event.getSceneX();
                posY = event.getSceneY();
            }
            if (event.getEventType() == MouseEvent.MOUSE_DRAGGED) {
                double deplacement = (event.getSceneX() - posX) + (event.getSceneY() - posY);
                //camera.getTransforms().add(); // A vous de compl ́eter
                camera.setTranslateZ(-1000 + (event.getSceneX() - posX) + (event.getSceneY() - posY));
                //System.out.println("Dragged on : (" + event.getSceneX() + ", " + event.getSceneY() + ")");
                //System.out.println("Distance : " + deplacement);
            }
        });

        AnimationTimer animationTimer = new AnimationTimer() {
            @Override
            public void handle(long time) {
                //System.out.println("Valeur de time : " + time);
                ry.setAngle(4*0.100530965); // A compl ́eter
                root.getTransforms().add(ry);
            }
        };
        animationTimer.start();

        theScene.addEventHandler(MouseEvent.ANY, event -> {
            if (event.getButton() == MouseButton.SECONDARY && event.getEventType() == MouseEvent.MOUSE_CLICKED) {
                System.out.println("You're in !");
                PickResult pickResult = event.getPickResult();
                if (pickResult.getIntersectedNode() != null) {
                    //Chopper les coordonnées à l'aide du pickResult
                    coordinates = pickResult.getIntersectedTexCoord();

                    posXLattitude = coordinates.getX();
                    posYLongitude = coordinates.getY();

                    System.out.println("posX : " +coordinates.getX());
                    System.out.println("posY : " +coordinates.getY());

                    Lattitude = 360*(posXLattitude-0.5);
                    double angle = Math.atan(Math.exp((0.5 - posYLongitude)/0.2678));
                    angle = angle*180/3.141592;
                    //System.out.println("Angle : " +angle);
                    Longitude = 2*angle - 90;

                    System.out.println("Longitude : " +Longitude);
                    System.out.println("Latitude : " +Lattitude);

                    Airports nearestAirport = w.findNearestAirport(Lattitude,Longitude);
                    System.out.println(nearestAirport);
                    terre.displayRedSphere(nearestAirport);


                    /*  Requete aviationStack           */
                    /*new Thread(new RequeteAviationStack(nearestAirport)).start();*/


                }
            }
        });

    }

    public static void main(String[] args) {
        launch(args);
    }
}