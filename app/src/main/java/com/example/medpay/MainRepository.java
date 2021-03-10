package com.example.medpay;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class MainRepository {
    private MainDao mainDao;
    private LiveData<List<MainEntity>> allEntities;


        public MainRepository(Application application) {
            RoomDb db = RoomDb.getInstance(application);
            mainDao = db.mainDao();
            allEntities = mainDao.getAll();
        }

        LiveData<List<MainEntity>> getAllEntities() {
            return allEntities;
        }

        public void insert (MainEntity mainEntity) {
            new insertAsyncTask(mainDao).execute(mainEntity);
        }

        private static class insertAsyncTask extends AsyncTask<MainEntity, Void, Void> {

            private MainDao mAsyncTaskDao;

            insertAsyncTask(MainDao dao) {
                mAsyncTaskDao = dao;
            }

            @Override
            protected Void doInBackground(final MainEntity... params) {
                mAsyncTaskDao.insert(params[0]);
                return null;
            }
        }
    }
