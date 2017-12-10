package com.sunzhijun.sqlitedemo;

import com.sunzhijun.utils.GetFilesUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class FolderActivity extends Activity implements OnItemClickListener,OnClickListener {

    private ListView folderLv;
    private TextView foldernowTv;
    private SimpleAdapter sAdapter;
    private List<Map<String, Object>> aList;
    private String baseFile;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mypage_folder);
        baseFile=GetFilesUtils.getInstance().getBasePath();


        folderLv=(ListView) findViewById(R.id.folder_list);
        foldernowTv=(TextView) findViewById(R.id.folder_now);
        foldernowTv.setText(baseFile);
        foldernowTv.setOnClickListener(this);
        aList=new ArrayList<Map<String,Object>>();
        sAdapter=new SimpleAdapter(this, aList,R.layout.folder_item, new String[]{"fImg","fName","fInfo"},
                new int[]{R.id.folder_img,R.id.folder_name,R.id.folder_info});
        folderLv.setAdapter(sAdapter);
        folderLv.setOnItemClickListener(this);
        try {
            loadFolderList(baseFile);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private void loadFolderList(String file) throws IOException{
        List<Map<String, Object>> list=GetFilesUtils.getInstance().getSonNode(file);
        if(list!=null){
            Collections.sort(list, GetFilesUtils.getInstance().defaultOrder());
            aList.clear();
            for(Map<String, Object> map:list){
                String fileType=(String) map.get(GetFilesUtils.FILE_INFO_TYPE);
                Map<String,Object> gMap=new HashMap<String, Object>();
                if(map.get(GetFilesUtils.FILE_INFO_ISFOLDER).equals(true)){
                    gMap.put("fIsDir", true);
                    gMap.put("fImg",R.drawable.filetype_folder );
                    gMap.put("fInfo", map.get(GetFilesUtils.FILE_INFO_NUM_SONDIRS)+"个文件夹和"+
                            map.get(GetFilesUtils.FILE_INFO_NUM_SONFILES)+"个文件");
                }else{
                    gMap.put("fIsDir", false);
                    if(fileType.equals("txt")||fileType.equals("text")){
                        gMap.put("fImg", R.drawable.filetype_text);
                    }else{
                        gMap.put("fImg", R.drawable.filetype_unknow);
                    }
                    gMap.put("fInfo","文件大小:"+GetFilesUtils.getInstance().getFileSize(map.get(GetFilesUtils.FILE_INFO_PATH).toString()));
                }
                gMap.put("fName", map.get(GetFilesUtils.FILE_INFO_NAME));
                gMap.put("fPath", map.get(GetFilesUtils.FILE_INFO_PATH));
                aList.add(gMap);
            }
        }else{
            aList.clear();
        }
        sAdapter.notifyDataSetChanged();
        foldernowTv.setText(file);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,
                            long id) {
        // TODO Auto-generated method stub
        try {
            if(aList.get(position).get("fIsDir").equals(true)){
                loadFolderList(aList.get(position).get("fPath").toString());
            }else{
                Toast.makeText(this, "这是文件，处理程序待添加", Toast.LENGTH_SHORT).show();
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        if(v.getId()==R.id.folder_now){
            try {
                String folder=GetFilesUtils.getInstance().getParentPath(foldernowTv.getText().toString());
                if(folder==null){
                    Toast.makeText(this, "无父目录，待处理", Toast.LENGTH_SHORT).show();
                }else{
                    loadFolderList(folder);
                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

}