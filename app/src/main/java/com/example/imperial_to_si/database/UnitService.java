package com.example.imperial_to_si.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.LinkedList;
import java.util.List;

import static com.example.imperial_to_si.database.DatabaseConstraints.COLUMN_FACTOR;
import static com.example.imperial_to_si.database.DatabaseConstraints.COLUMN_ID;
import static com.example.imperial_to_si.database.DatabaseConstraints.COLUMN_NAME;
import static com.example.imperial_to_si.database.DatabaseConstraints.COLUMN_UNIT_SI;

public class UnitService {

    private SQLiteDatabase database;
    private DbHandler handler;

    public UnitService(Context context) {

        // TODO here should not be created new DbHandler because during that new database is being created
        handler = new DbHandler(context);
        database = handler.getReadableDatabase();
    }

    public List<Unit> findAllUnits() {

        List<Unit> results = new LinkedList<>();

        Cursor cursor = handler.getAllUnits(database);
        while (cursor.moveToNext()) {

            Integer id = cursor.getColumnIndex(COLUMN_ID);
            String name = cursor.getString(cursor.getColumnIndex(COLUMN_NAME));
            Double factor = cursor.getDouble(cursor.getColumnIndex(COLUMN_FACTOR));
            String unitSI = cursor.getString(cursor.getColumnIndex(COLUMN_UNIT_SI));

            results.add(new Unit(id, name, factor, unitSI));
        }

        return results;
    }
}
