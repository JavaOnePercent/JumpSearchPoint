import common.Coordinates;
import common.constants.Direction;
import common.Point;
import static common.constants.Map.*;
import static common.constants.Direction.*;
import static common.constants.Size.*;
import static utils.Calculates.*;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class NavigatorMP implements Navigator
{
    private char[][] map;

    private Point startPoint;
    private Point finishPoint;

    private Queue<Point> queue = new PriorityQueue<Point>();
    private List<Point> road = new ArrayList<Point>();

    public char[][] searchRoute(char[][] map)
    {
        this.map = map;

        if(!isCheckedSizeMap(map.length, map[0].length))
        {
            return null;
        }

        if(!isFoundMainPoints())
        {
            return null;
        }

        if(isFoundOptimalPoints())
        {
            drawPathOnMap();
            return map;
        }

        return null;
    }

    private boolean isFoundMainPoints()
    {
        for (int x = 0; x < map.length; x++)
        {
            for (int y = 0; y < map[x].length; y++)
            {
                if (map[x][y] == START)
                {
                    startPoint = initPoint(x, y);
                    if(finishPoint != null)
                    {
                        return true;
                    }
                }
                else if (map[x][y] == FINISH)
                {
                    finishPoint = initPoint(x, y);
                    if(startPoint != null)
                    {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean isFoundOptimalPoints()
    {
        int startCost = calculateManhattanDistance(startPoint, finishPoint);

        startPoint.setCost(startCost);
        queue.add(startPoint);

        while(!queue.isEmpty())
        {
            Point current = queue.poll();
            if(current.equals(finishPoint))
            {
                finishPoint = current;
                road.add(current);
                return true;
            }

            road.add(current);
            searchNeighbour(current);
        }

        return false;
    }

    private void searchNeighbour(Point point)
    {
        List<Point> neighbours = getNeighbour(point);
        for(Point p: neighbours)
        {
            p = searchJumpPoint(p, calculateComputeDirection(point, p));
            if (p != null && !road.contains(p) && !queue.contains(p))
            {
                p.setCost(calculateManhattanDistance(p, finishPoint) + calculateManhattanDistance(p, startPoint));
                p.setParentPoint(point);
                queue.add(p);
            }
        }
    }


    private List<Point> getNeighbour(Point point)
    {
        List<Point> neighbours = new ArrayList<Point>();

        int coorX = point.getCoordinates().getX();
        int coorY = point.getCoordinates().getY();

        if (!isOutMap(coorX - 1, coorY))
        {
            Point newPoint = createPoint(coorX - 1, coorY, point);
            neighbours.add(newPoint);
        }

        if (!isOutMap(coorX + 1, coorY))
        {
            Point newPoint = createPoint(coorX + 1, coorY, point);
            neighbours.add(newPoint);
        }

        if (!isOutMap(coorX, coorY - 1))
        {
            Point newPoint = createPoint(coorX, coorY - 1, point);
            neighbours.add(newPoint);
        }

        if (!isOutMap(coorX, coorY + 1))
        {
            Point newPoint = createPoint(coorX, coorY + 1, point);
            neighbours.add(newPoint);
        }

        return neighbours;
    }

    private Point searchJumpPoint(Point point, Direction direction)
    {
        int pointX = point.getCoordinates().getX();
        int pointY = point.getCoordinates().getY();

        if (isOutMap(pointX, pointY))
        {
            return null;
        }

        if (point.equals(finishPoint))
        {
            return point;
        }

        switch(direction)
        {
            case UP:
            case DOWN:
                if (isCheckedForcedNeighbor(pointX, pointY, direction))
                {
                    return point;
                }
                Point leftJumpPoint = searchJumpPoint(getNextJumpPoint(point, LEFT), LEFT);
                Point rightJumpPoint = searchJumpPoint(getNextJumpPoint(point, RIGHT), RIGHT);

                if ((leftJumpPoint != null) || (rightJumpPoint != null))
                {
                    return point;
                }
            case LEFT:
            case RIGHT:
                if (isCheckedForcedNeighbor(pointX, pointY, direction))
                {
                    return point;
                }
        }

        return searchJumpPoint(getNextJumpPoint(point, direction), direction);
    }

    private Point getNextJumpPoint(Point point, Direction direction)
    {
        int x = point.getCoordinates().getX();
        int y = point.getCoordinates().getY();

        switch (direction)
        {
            case LEFT:
                return createPoint(x, y - 1, point);
            case RIGHT:
                return createPoint(x, y + 1, point);
            case UP:
                return createPoint(x - 1, y, point);
            case DOWN:
                return createPoint(x + 1, y, point);
            default:
                return point;
        }
    }

    private Point initPoint(int coorX, int coorY)
    {
        Coordinates coordinates = new Coordinates(coorX, coorY);
        Point point = new Point();
        point.setCoordinates(coordinates);
        return point;
    }

    private Point createPoint(int coorX, int coorY, Point parentPoint)
    {
        Coordinates coordinates = new Coordinates(coorX, coorY);
        Point point = new Point(coordinates, parentPoint);
        point.setCost(calculateManhattanDistance(startPoint, point) + calculateManhattanDistance(finishPoint, point));
        return point;
    }

    private void drawPathOnMap()
    {
        Point currentPoint = finishPoint;
        Point parentPoint = currentPoint.getParentPoint();

        while(!currentPoint.equals(startPoint))
        {
            switch (calculateComputeDirection(parentPoint, currentPoint))
            {
                case LEFT:
                case RIGHT:
                    drawPathHorizontal(parentPoint.getCoordinates().getY(), currentPoint.getCoordinates().getY(),
                            parentPoint.getCoordinates().getX());
                    break;
                case UP:
                case DOWN:
                    drawPathVertical(parentPoint.getCoordinates().getX(), currentPoint.getCoordinates().getX(),
                            parentPoint.getCoordinates().getY());
                    break;
                default:
                    break;
            }
            Point temp = parentPoint;
            parentPoint = parentPoint.getParentPoint();
            currentPoint = temp;
        }

        map[startPoint.getCoordinates().getX()][startPoint.getCoordinates().getY()] = START;
        map[finishPoint.getCoordinates().getX()][finishPoint.getCoordinates().getY()] = FINISH;
    }

    private void drawPathHorizontal(int start, int finish, int row)
    {
        int from = Math.min(start, finish);
        int to = Math.max(start, finish);

        for (int y = from; y <= to; y++)
        {
            map[row][y] = WAY;
        }
    }

    private void drawPathVertical(int start, int finish, int column)
    {
        int from = Math.min(start, finish);
        int to = Math.max(start, finish);

        for (int x = from; x <= to; x++)
        {
            map[x][column] = WAY;
        }
    }

    private boolean isOutMap(int x, int y)
    {
        return !((x >= 0) && (x <= map.length - 1) && (y >= 0) && (y <= map[x].length - 1)) || map[x][y] == WALL;
    }

    private boolean isCheckedForcedNeighbor(int x, int y, Direction direction)
    {
        switch(direction)
        {
            case LEFT:
                return (!isOutMap(x - 1, y) && isOutMap(x - 1, y + 1)) ||
                        (!isOutMap(x + 1, y) && isOutMap(x + 1, y + 1));
            case RIGHT:
                return (!isOutMap(x - 1, y) && isOutMap(x - 1, y - 1)) ||
                        (!isOutMap(x + 1, y) && isOutMap(x + 1, y - 1));
            case UP:
                return (!isOutMap(x, y - 1) && isOutMap(x + 1, y - 1)) ||
                        (!isOutMap(x, y + 1) && isOutMap(x + 1, y + 1));
            case DOWN:
                return (!isOutMap(x, y - 1) && isOutMap(x - 1, y - 1)) ||
                        (!isOutMap(x, y + 1) && isOutMap(x - 1, y + 1));
            default:
                return false;
        }
    }

    private boolean isCheckedSizeMap(int horizontalSize, int verticalSize)
    {
        return horizontalSize >= MIN_SIZE && horizontalSize <= MAX_SIZE &&
                verticalSize >= MIN_SIZE && verticalSize <= MAX_SIZE;
    }
}