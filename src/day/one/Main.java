package day.one;

import java.io.*;

public class Main {
    public static void main(String[] args) {
        int[] input = getInputData();

        System.out.println(solve(input));
    }

    public static int solve(int[] input) {
        for (int j : input) {
            for (int k : input) {
                for (int l : input) {
                    if (j + k + l == 2020) {
                        return j * k * l;
                    }
                }
            }
        }
        return -1;
    }

    public static int[] getInputData() {
        String inputData = null;

        try {
            inputData = readTxtFile("./src/day/one/input.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }

        String[] inputStringArray = inputData.split("\n");
        int[] inputArray = new int[inputStringArray.length];
        for (int i = 0; i < inputStringArray.length; i++) inputArray[i] = Integer.parseInt(inputStringArray[i]);

        return inputArray;
    }

    public static String readTxtFile(String fileName) throws IOException {
//        DataInputStream in = null;
//        String data = null;
//
//        try {
//            in = new DataInputStream(new BufferedInputStream(new FileInputStream(fileName)));
//            data = in.readUTF();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            in.close();
//        }

        //return data;
        return "997\n" +
                "1582\n" +
                "1790\n" +
                "1798\n" +
                "1094\n" +
                "1831\n" +
                "1879\n" +
                "1730\n" +
                "1995\n" +
                "1702\n" +
                "1680\n" +
                "1869\n" +
                "1964\n" +
                "1777\n" +
                "1862\n" +
                "1928\n" +
                "1997\n" +
                "1741\n" +
                "1604\n" +
                "1691\n" +
                "1219\n" +
                "1458\n" +
                "1749\n" +
                "1717\n" +
                "1786\n" +
                "1665\n" +
                "1724\n" +
                "1998\n" +
                "1589\n" +
                "1828\n" +
                "1953\n" +
                "1848\n" +
                "1500\n" +
                "1590\n" +
                "1968\n" +
                "1948\n" +
                "1323\n" +
                "1800\n" +
                "1986\n" +
                "679\n" +
                "1907\n" +
                "1916\n" +
                "1820\n" +
                "1661\n" +
                "1479\n" +
                "1808\n" +
                "1824\n" +
                "1825\n" +
                "1952\n" +
                "1666\n" +
                "1541\n" +
                "1791\n" +
                "1906\n" +
                "1638\n" +
                "1557\n" +
                "1999\n" +
                "1710\n" +
                "1549\n" +
                "1912\n" +
                "1974\n" +
                "1628\n" +
                "1748\n" +
                "1411\n" +
                "1978\n" +
                "1865\n" +
                "1932\n" +
                "1839\n" +
                "1892\n" +
                "1981\n" +
                "1807\n" +
                "357\n" +
                "912\n" +
                "1443\n" +
                "1972\n" +
                "1816\n" +
                "1890\n" +
                "1029\n" +
                "1175\n" +
                "1522\n" +
                "1750\n" +
                "2001\n" +
                "1655\n" +
                "1955\n" +
                "1949\n" +
                "1660\n" +
                "233\n" +
                "1891\n" +
                "1994\n" +
                "1934\n" +
                "1908\n" +
                "1573\n" +
                "1712\n" +
                "1622\n" +
                "1770\n" +
                "1574\n" +
                "1778\n" +
                "1851\n" +
                "2004\n" +
                "1818\n" +
                "1200\n" +
                "1229\n" +
                "1110\n" +
                "1005\n" +
                "1716\n" +
                "1765\n" +
                "1835\n" +
                "1773\n" +
                "15\n" +
                "1914\n" +
                "1833\n" +
                "1689\n" +
                "1843\n" +
                "1718\n" +
                "1872\n" +
                "390\n" +
                "1941\n" +
                "1178\n" +
                "1670\n" +
                "1899\n" +
                "1864\n" +
                "1913\n" +
                "2010\n" +
                "1855\n" +
                "1797\n" +
                "1767\n" +
                "1673\n" +
                "1657\n" +
                "1607\n" +
                "1305\n" +
                "1341\n" +
                "1662\n" +
                "1845\n" +
                "1980\n" +
                "1534\n" +
                "1789\n" +
                "1876\n" +
                "1849\n" +
                "1926\n" +
                "1958\n" +
                "977\n" +
                "1709\n" +
                "1647\n" +
                "1832\n" +
                "1785\n" +
                "1854\n" +
                "1667\n" +
                "1679\n" +
                "1970\n" +
                "1186\n" +
                "2000\n" +
                "1681\n" +
                "1684\n" +
                "1614\n" +
                "1988\n" +
                "1561\n" +
                "1594\n" +
                "1636\n" +
                "1327\n" +
                "1696\n" +
                "1915\n" +
                "1045\n" +
                "1829\n" +
                "1079\n" +
                "1295\n" +
                "1213\n" +
                "1714\n" +
                "1992\n" +
                "1984\n" +
                "1951\n" +
                "1687\n" +
                "1842\n" +
                "1792\n" +
                "87\n" +
                "1732\n" +
                "428\n" +
                "1799\n" +
                "1850\n" +
                "1962\n" +
                "1629\n" +
                "1965\n" +
                "1142\n" +
                "1040\n" +
                "131\n" +
                "1844\n" +
                "1454\n" +
                "1779\n" +
                "1369\n" +
                "1960\n" +
                "1887\n" +
                "1725\n" +
                "1893\n" +
                "1465\n" +
                "1676\n" +
                "1826\n" +
                "1462\n" +
                "1408\n" +
                "1937\n" +
                "1643\n" +
                "1069\n" +
                "1759\n";
    }
}
