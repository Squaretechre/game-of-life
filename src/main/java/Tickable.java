import java.awt.*;

public interface Tickable {
    void tickFor(Point currentPoint);
    void finishedTicking();
}
