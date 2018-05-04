package bicome.animations;

public enum ScaleValue
{
    SMALLEST(0.5),
    SMALLER(0.75),
    MEDIUM(1),
    BIGGER(1.25),
    BIGGEST(2);

    private double scaleValue;

    ScaleValue(double scaleValue)
    {
        this.scaleValue = scaleValue;
    }

    public double getScaleValue()
    {
        return  scaleValue;
    }
}

