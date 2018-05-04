package bicome.animations;

import javafx.animation.ScaleTransition;
import javafx.scene.control.Control;
import javafx.util.Duration;

public class ViewAnimations
{
    private static final Duration ANIM_DURATION = Duration.millis(250);

    /**
     * Scales the control with the given ScaleValue
     * @param control Control to scale
     * @param value value for whether scale positively or negatively takes value as ScaleValue enum
     */
    public static void scaleControl(Control control, ScaleValue value)
    {
        ScaleTransition transition = new ScaleTransition(ANIM_DURATION, control);
        double scaleRate = value.getScaleValue();
        transition.setToX(scaleRate);
        transition.setToY(scaleRate);

        transition.play();
    }
}
