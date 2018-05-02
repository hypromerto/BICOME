package bicome.animations;

import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.scene.control.Control;
import javafx.util.Duration;

public class ViewAnimations
{
    private static final Duration ANIM_DURATION = Duration.millis(250);

    /**
     * @param control
     * @param value
     */
    public static void zoomControl(Control control, ScaleValue value)
    {
        ScaleTransition transition = new ScaleTransition(ANIM_DURATION, control);
        double scaleRate;
        switch (value) {
            case SMALLEST:
                scaleRate = ScaleValue.SMALLEST.GetScaleValue();
                break;
            case SMALLER:
                scaleRate = ScaleValue.SMALLER.GetScaleValue();
                break;
            case MEDIUM:
                scaleRate = ScaleValue.MEDIUM.GetScaleValue();
                break;
            case BIGGER:
                scaleRate = ScaleValue.BIGGER.GetScaleValue();
                break;
            case BIGGEST:
                scaleRate = ScaleValue.BIGGEST.GetScaleValue();
                break;
            default:
                scaleRate = ScaleValue.MEDIUM.GetScaleValue();
                break;
        }
        transition.setToX(scaleRate);
        transition.setToY(scaleRate);

        transition.play();
    }
}
