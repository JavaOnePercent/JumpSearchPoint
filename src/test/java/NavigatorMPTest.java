import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class NavigatorMPTest
{
    private Navigator navigator = new NavigatorMP();

    @Test
    public void test1()
    {
        char[][] map =
        {
            "...@.".toCharArray(),
            ".####".toCharArray(),
            ".....".toCharArray(),
            "####.".toCharArray(),
            ".X...".toCharArray()
        };

        char[][] way =
        {
            "+++@.".toCharArray(),
            "+####".toCharArray(),
            "+++++".toCharArray(),
            "####+".toCharArray(),
            ".X+++".toCharArray()
        };

        char[][] result = navigator.searchRoute(map);

        Assert.assertTrue(Arrays.deepEquals(result, way));
    }

    @Test
    public void test2()
    {
        char[][] map =
        {
            "..X..".toCharArray(),
            "#####".toCharArray(),
            ".....".toCharArray(),
            ".@...".toCharArray(),
            ".....".toCharArray()
        };

        char[][] result = navigator.searchRoute(map);

        Assert.assertTrue(result == null);
    }

    @Test
    public void test3()
    {
        char[][] map =
        {
            "....@".toCharArray(),
            "#.###".toCharArray(),
            ".....".toCharArray(),
            "....X".toCharArray(),
            ".....".toCharArray()
        };

        char[][] way1 =
        {
            ".+++@".toCharArray(),
            "#+###".toCharArray(),
            ".++++".toCharArray(),
            "....X".toCharArray(),
            ".....".toCharArray()
        };

        char[][] way2 =
        {
            ".+++@".toCharArray(),
            "#+###".toCharArray(),
            ".+++.".toCharArray(),
            "...+X".toCharArray(),
            ".....".toCharArray()
        };

        char[][] way3 =
        {
            ".+++@".toCharArray(),
            "#+###".toCharArray(),
            ".++..".toCharArray(),
            "..++X".toCharArray(),
            ".....".toCharArray()
        };

        char[][] way4 =
        {
            ".+++@".toCharArray(),
            "#+###".toCharArray(),
            ".+...".toCharArray(),
            ".+++X".toCharArray(),
            ".....".toCharArray()
        };

        char[][] result = navigator.searchRoute(map);

        boolean w1 = Arrays.deepEquals(result, way1);
        boolean w2 = Arrays.deepEquals(result, way2);
        boolean w3 = Arrays.deepEquals(result, way3);
        boolean w4 = Arrays.deepEquals(result, way4);

        Assert.assertTrue(w1 || w2 || w3 || w4);
    }

    @Test
    public void test4()
    {
        char[][] map =
        {
            ".......................".toCharArray(),
            ".@...................X.".toCharArray(),
            ".......................".toCharArray(),
        };

        char[][] way =
        {
            ".......................".toCharArray(),
            ".@+++++++++++++++++++X.".toCharArray(),
            ".......................".toCharArray(),
        };

        char[][] result = navigator.searchRoute(map);

        Assert.assertTrue(Arrays.deepEquals(result, way));
    }

    @Test
    public void test5()
    {

        char[][] map =
        {
            "...............".toCharArray(),
            "...............".toCharArray(),
            "..###########..".toCharArray(),
            "............#..".toCharArray(),
            "............#..".toCharArray(),
            "............#..".toCharArray(),
            "............#.X".toCharArray(),
            "............#..".toCharArray(),
            "............#..".toCharArray(),
            "............#..".toCharArray(),
            "............#..".toCharArray(),
            "............#..".toCharArray(),
            "@.###########..".toCharArray(),
            "...............".toCharArray(),
            "...............".toCharArray(),
        };

        char[][] way1 =
        {
            "...............".toCharArray(),
            "...............".toCharArray(),
            "..###########..".toCharArray(),
            "............#..".toCharArray(),
            "............#..".toCharArray(),
            "............#..".toCharArray(),
            "............#.X".toCharArray(),
            "............#.+".toCharArray(),
            "............#.+".toCharArray(),
            "............#.+".toCharArray(),
            "............#.+".toCharArray(),
            "............#.+".toCharArray(),
            "@+###########++".toCharArray(),
            ".+++++++++++++.".toCharArray(),
            "...............".toCharArray(),
        };

        char[][] way2 =
        {
            "...............".toCharArray(),
            "...............".toCharArray(),
            "..###########..".toCharArray(),
            "............#..".toCharArray(),
            "............#..".toCharArray(),
            "............#..".toCharArray(),
            "............#+X".toCharArray(),
            "............#+.".toCharArray(),
            "............#+.".toCharArray(),
            "............#+.".toCharArray(),
            "............#+.".toCharArray(),
            "............#+.".toCharArray(),
            "@.###########+.".toCharArray(),
            "++++++++++++++.".toCharArray(),
            "...............".toCharArray(),
        };

        char[][] way3 =
        {
            "...............".toCharArray(),
            "...............".toCharArray(),
            "..###########..".toCharArray(),
            "............#..".toCharArray(),
            "............#..".toCharArray(),
            "............#..".toCharArray(),
            "............#+X".toCharArray(),
            "............#+.".toCharArray(),
            "............#+.".toCharArray(),
            "............#+.".toCharArray(),
            "............#+.".toCharArray(),
            "............#+.".toCharArray(),
            "@+###########+.".toCharArray(),
            ".+++++++++++++.".toCharArray(),
            "...............".toCharArray(),
        };

        char[][] way4 =
        {
            "...............".toCharArray(),
            "...............".toCharArray(),
            "..###########..".toCharArray(),
            "............#..".toCharArray(),
            "............#..".toCharArray(),
            "............#..".toCharArray(),
            "............#.X".toCharArray(),
            "............#.+".toCharArray(),
            "............#.+".toCharArray(),
            "............#.+".toCharArray(),
            "............#.+".toCharArray(),
            "............#.+".toCharArray(),
            "@+###########.+".toCharArray(),
            ".++++++++++++++".toCharArray(),
            "...............".toCharArray(),
        };

        char result[][] = navigator.searchRoute(map);

        boolean w1 = Arrays.deepEquals(result, way1);
        boolean w2 = Arrays.deepEquals(result, way2);
        boolean w3 = Arrays.deepEquals(result, way3);
        boolean w4 = Arrays.deepEquals(result, way4);

        Assert.assertTrue(w1 || w2 || w3 || w4);
    }
}
