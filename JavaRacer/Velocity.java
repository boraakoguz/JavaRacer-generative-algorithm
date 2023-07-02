package JavaRacer;

public class Velocity {
    public double X, Y;
    public int angle;
    public Velocity(){
        this.X = 0;
        this.Y = 0;
        this.angle = 0;
    }
    public void accelerate(double amount){
        if(amount<0 && (netVelocity()<amount||netVelocity()<1)){ // sets velocity to (0,0) to prevent drifting at low speeds and reversing.
                this.X = 0;
                this.Y = 0;
        }
        else if(Math.sqrt(Math.pow(this.X+amount*Math.cos(Math.toRadians(angle)),2)+Math.pow(this.Y+amount*Math.sin(Math.toRadians(angle)), 2))<=10){
            this.X += amount*Math.cos(Math.toRadians(angle));
            this.Y += amount*Math.sin(Math.toRadians(angle));
        }
    }
    public void rotate(int degree){
        angle += degree;
        fixAngle();
        double newX = Math.cos(Math.toRadians(degree))*X-Math.sin(Math.toRadians(degree))*Y;
        double newY = Math.sin(Math.toRadians(degree))*X+Math.cos(Math.toRadians(degree))*Y;
        this.X = newX;
        this.Y = newY;
    }
    public void fixAngle(){
        if(angle>360){
            angle -= 360;
        }
        else if(angle<0){
            angle = 360 + angle;
        }
    }
    public String toString(){
        return this.X + " " + this.Y + " "+ this.angle + " " + netVelocity();
    }
    public double netVelocity(){
        return Math.sqrt(Math.pow(this.X,2)+Math.pow(this.Y,2));
    }
}
