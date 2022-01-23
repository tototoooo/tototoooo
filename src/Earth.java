import javafx.animation.AnimationTimer;
import javafx.geometry.Point3D;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Sphere;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;

import java.awt.*;
import java.util.ArrayList;

public class Earth extends Group {
    private Rotate r = new Rotate(0, new Point3D(0,1,0));
    private Sphere s = new Sphere(300);
    ArrayList<Sphere> yellowSphere = new ArrayList<>();

    public Sphere createSphere(Airports a, Color c){
        Double X,Y,Z;
        X = s.getRadius()*Math.cos(Math.toRadians(a.getLatitude()-13))*Math.sin(Math.toRadians(a.getLongitude()));
        Y = -(s.getRadius()*Math.sin(Math.toRadians(a.getLatitude()-13)));
        Z = -(s.getRadius()*Math.cos(Math.toRadians(a.getLatitude()-13))*Math.cos(Math.toRadians(a.getLongitude())));
        Sphere lil = new Sphere(4);
        lil.setMaterial(new PhongMaterial(c));
        lil.getTransforms().add(new Translate(X,Y,Z));
        return lil;
    }

    public void displayRedSphere(Airports a){
        Sphere lil = createSphere(a, Color.RED);
        this.getChildren().add(lil);
    }

    public Earth() {
        this.getChildren().add(s);

        PhongMaterial pm = new PhongMaterial();
        try {
            Image im = new Image("file:///C:/Users/DIARIETOU/IdeaProjects/GLOBE/Resources/earth_lights_4800.png");
            pm.setSpecularMap(im);
            pm.setDiffuseMap(im);
            pm.setSelfIlluminationMap(im);
            this.getTransforms().add(r);
            s.setMaterial(pm);
            this.setOpacity(1);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        AnimationTimer anim = new AnimationTimer() {
            double angle = 0;
            @Override
            public void handle(long time) {
                r.setAngle(time/70000000.2);
            }
        };
        anim.start();
    }
}