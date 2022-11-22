public class Snake {
    private int current_head_x=0;
    private int current_head_y=0;
    private int length=1;
    private boolean turn_down_initiated_status=false;
    private int current_score=0;
    

    private String current_direction;

    public static final String FORWARD="forward";
    public static final String BACKWARD="backward";
    public static final String UPWARD="upward";
    public static final String DOWNWARD="downward";



    public Snake(int initial_x,int initial_y,int length) {
        this.current_head_x=initial_x;
        this.current_head_y=initial_y;
        this.length=length;
    }

    public int getLength() {
        return length;
    }

    public void setCurrent_direction(String current_direction) {
        this.current_direction = current_direction;
    }

    public int getCurrent_head_x() {
        return current_head_x;
    }

    public int getCurrent_head_y() {
        return current_head_y;
    }

    public String getCurrent_direction() {
        return current_direction;
    }

    
    public int getScore(){
        return current_score;
    }


    protected int moveForward() {
        this.turn_down_initiated_status=false;
        this.current_direction=Snake.FORWARD;
        this.current_head_x++;
        return 0;
    }

    protected int moveBackward() {
        this.turn_down_initiated_status=false;
        this.current_direction=Snake.BACKWARD;
        this.current_head_x--;
        return 0;
    }

    protected int moveUp() {
        this.turn_down_initiated_status=false;
        this.current_direction=Snake.UPWARD;
        this.current_head_y--;
        return 0;
    }

    protected int moveDown() {
        if(turn_down_initiated_status==false){
            turnDown();
        }
        this.current_direction=Snake.DOWNWARD;
        this.current_head_y++;
        return 0;
    }

    private void turnDown() {
        this.current_direction=Snake.DOWNWARD;
        this.current_head_y=current_head_y+length;
        turn_down_initiated_status=true;
    }

    public void incrementScore(){
        current_score++;
    }

    public void incrementLength(){
        length++;
    }

}
