package com.imdb.SimulacionCarnet;

import java.util.ArrayList;

/**
 * Created by Martin on 08/04/2017.
 */

public class Repository {
    private static ArrayList<Process> processes = null;

    public Repository()
    {
    }

    public static ArrayList<Process> getProcesses()
    {
        if (processes == null)
        {
            processes = new ArrayList<>();
        }
        return processes;
    }
}
