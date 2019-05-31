package utils;

import common.Point;
import common.constants.Direction;

import static common.constants.Direction.*;

public class Calculates
{
    public static int calculateManhattanDistance(Point firstPoint, Point secondPoint)
    {
        int abscissaDistance = Math.abs(firstPoint.getCoordinates().getX() - secondPoint.getCoordinates().getX());
        int ordinateDistance = Math.abs(firstPoint.getCoordinates().getY() - secondPoint.getCoordinates().getY());
        int manhattanDistance = abscissaDistance + ordinateDistance;
        return manhattanDistance;
    }

    public static Direction calculateComputeDirection(Point firstPoint, Point secondPoint)
    {
        int abscissaFirstPoint = firstPoint.getCoordinates().getX();
        int ordinateFirstPoint = firstPoint.getCoordinates().getY();

        int abscissaSecondPoint = secondPoint.getCoordinates().getX();
        int ordinateSecondPoint = secondPoint.getCoordinates().getY();

        if (abscissaFirstPoint - abscissaSecondPoint == 0)
        {
            if (ordinateFirstPoint - ordinateSecondPoint > 0)
            {
                return LEFT;
            }
            else
            {
                return RIGHT;
            }
        }
        else
        {
            if (abscissaFirstPoint - abscissaSecondPoint > 0)
            {
                return UP;
            }
            else
            {
                return DOWN;
            }
        }
    }
}
