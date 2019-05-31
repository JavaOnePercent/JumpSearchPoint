package common;

public class Point implements Comparable<Point>
{
    private Coordinates coordinates;
    private Point parentPoint;
    private int cost;

    public Point()
    {

    }

    public Point(Coordinates coordinates, Point parentPoint)
    {
        this.coordinates = coordinates;
        this.parentPoint = parentPoint;
    }

    public Coordinates getCoordinates()
    {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates)
    {
        this.coordinates = coordinates;
    }

    public Point getParentPoint()
    {
        return parentPoint;
    }

    public void setParentPoint(Point parentPoint)
    {
        this.parentPoint = parentPoint;
    }

    public void setCost(int cost)
    {
        this.cost = cost;
    }

    @Override
    public int compareTo(Point point)
    {
        return this.cost - point.cost;
    }

    @Override
    public boolean equals(Object object)
    {
        if (object == this)
        {
            return true;
        }

        if (!(object instanceof Point))
        {
            return false;
        }

        Point point = (Point) object;
        return ((point.getCoordinates().getX() == getCoordinates().getX()) &&
                (point.getCoordinates().getY() == getCoordinates().getY()));
    }
}
