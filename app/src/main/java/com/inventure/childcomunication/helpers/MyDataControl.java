package com.inventure.childcomunication.helpers;

import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.os.Handler;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;


import com.inventure.childcomunication.activity.Setting;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

public class MyDataControl {

//    private static DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
//    private static Uri uri ;

//    private static void  downloadData(Uri uri , String file , Context context , String ex){
//
//        DownloadManager.Request request = new DownloadManager.Request(uri);
//
//        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI|DownloadManager.Request.NETWORK_MOBILE);
//
//        request.setTitle("DownLoads");
//        request.setDescription("Download file");
//        request.allowScanningByMediaScanner();
//        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE);
//        request.setDestinationInExternalPublicDir(file,"مياه"+ex);
//        DownloadManager downloadManager = (DownloadManager)context.getSystemService(Context.DOWNLOAD_SERVICE);
//        downloadManager.enqueue(request);
//
//
//
//
//
//    }
//    public   static class GetData extends AsyncTask<Void , Void , Void> {
//
//        Handler handler ;
//        Context context ;
//        TextView textView ;
//        ProgressBar  progressBar;
//
//        public GetData(Handler handler , Context context , TextView textView , ProgressBar  progressBar ) {
//            this.handler = handler ;
//            this.context = context ;
//            this.progressBar = progressBar ;
//            this.textView = textView;
//
//
//
//        }
//
//
//
//        @Override
//        protected Void doInBackground(Void... voids) {
//
//
//
//            reference.child("song").addValueEventListener(new ValueEventListener() {
//                @Override
//                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                    handler=null;
//                     String x = dataSnapshot.getValue().toString();
//                       uri = Uri.parse(x);
//
//                    downloadData(uri, "/MusicFile", context,".mp3");
//
//
//
//                }
//
//                @Override
//                public void onCancelled(@NonNull DatabaseError databaseError) {
//
//                    progressBar.setVisibility(View.GONE);
//                    textView.setText("please check your internet connection");
//
//                }
//            });
//            reference.child("img").addValueEventListener(new ValueEventListener() {
//                @Override
//                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                    handler=null;
//                    String x = dataSnapshot.getValue().toString();
//                    uri = Uri.parse(x);
//
//                    downloadData(uri, "/PhotoCh", context,".jpg");
//
//
//
//                }
//
//                @Override
//                public void onCancelled(@NonNull DatabaseError databaseError) {
//
//                    progressBar.setVisibility(View.GONE);
//                    textView.setText("please check your internet connection");
//
//                }
//            });
//
//
//            return null;
//        }
//    }


    public static void createFileOfApp(){

        File wallpaperDirectory = new File(Environment.getExternalStorageDirectory().getAbsolutePath()+"/Child Communication/Data/");

        wallpaperDirectory.mkdirs();


    }
    //delete folder
    public static void deleteRecursive(File fileOrDirectory) {

        if (fileOrDirectory.isDirectory()) {
            for (File child : fileOrDirectory.listFiles()) {
                deleteRecursive(child);
            }
        }

        fileOrDirectory.delete();
    }


    public static void deleteFromFavourite(String root,String root2){

        File file = new File(root);
        file.delete();
        File file2 = new File(root2);
        file2.delete();

    }
    public static void CopyRAWtoPhone(int id, String path, Context context) throws IOException {
        InputStream in =context.getResources().openRawResource(id);
        FileOutputStream out = new FileOutputStream(path);
        byte[] buff = new byte[1024];
        int read = 0;
        try {
            while ((read = in.read(buff)) > 0) {
                out.write(buff, 0, read);
            }
        } finally {
            in.close();
            out.close();
        }
    }

    public static ArrayList<HashMap<String,String>> getPlayList(String rootPath ) {
        ArrayList<HashMap<String,String>> fileList = new ArrayList<>();


        try {
            File rootFolder = new File(rootPath);
            File[] files = rootFolder.listFiles(); //here you will get NPE if directory doesn't contains  any file,handle it like this.
            for (File file : files) {
                if (file.isDirectory()) {
                    if (getPlayList(file.getAbsolutePath()) != null) {
                        fileList.addAll(getPlayList(file.getAbsolutePath()));
                    } else {
                        break;
                    }
                } else if (file.getName().endsWith(".mp3")) {
                    HashMap<String, String> song = new HashMap<>();
                    song.put("file_path", file.getAbsolutePath());
                    song.put("file_name", file.getName());
                    fileList.add(song);
                }
            }
            return fileList;
        } catch (Exception e) {


            return fileList;
        }
    }

    public static ArrayList<HashMap<String,String>> getImage(String rootPath ) {
        ArrayList<HashMap<String,String>> fileList = new ArrayList<>();


        try {
            File rootFolder = new File(rootPath);
            File[] files = rootFolder.listFiles(); //here you will get NPE if directory doesn't contains  any file,handle it like this.
            for (File file : files) {
                if (file.isDirectory()) {
                    if (getPlayList(file.getAbsolutePath()) != null) {
                        fileList.addAll(getPlayList(file.getAbsolutePath()));
                    } else {
                        break;
                    }
                } else if (file.getName().endsWith(".jpg")) {
                    HashMap<String, String> song = new HashMap<>();
                    song.put("file_path", file.getAbsolutePath());
                    song.put("file_name", file.getName());
                    fileList.add(song);
                }
            }
            return fileList;
        } catch (Exception e) {


            return fileList;
        }
    }
}
