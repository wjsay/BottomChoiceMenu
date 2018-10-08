package wjsay.com.bottomselect;

import android.app.Activity;
import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity  {
    private Button btn_bottom_dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_bottom_dialog = findViewById(R.id.tv_bottom_dialog);
        btn_bottom_dialog.setOnClickListener(new BottomMenuOnClinkListener(this));
    }


    class BottomMenuOnClinkListener implements View.OnClickListener {
        Activity activity;
        public BottomMenuOnClinkListener(Activity activity) {
            this.activity = activity;
        }
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.tv_bottom_dialog:
                    //弹出对话框
                    setDialog();
                    break;
                case R.id.tv_choose_img:
                    //选择照片按钮
                    Toast.makeText(activity, "请选择照片", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.tv_open_camera:
                    //拍照按钮
                    Toast.makeText(activity, "即将打开相机", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.btn_cancel:
                    //取消按钮
                    // mCameraDialog.dimiss(); // 取消菜单
                    Toast.makeText(activity, "取消", Toast.LENGTH_SHORT).show();
                    break;

            }
        }
    }

    private void setDialog() {
        Dialog mCameraDialog = new Dialog(this, R.style.BottomDialog);
        LinearLayout root = (LinearLayout) LayoutInflater.from(this).inflate(
                R.layout.bottom_dialog, null);
        //初始化视图
        root.findViewById(R.id.tv_choose_img).setOnClickListener(new BottomMenuOnClinkListener(this));
        root.findViewById(R.id.tv_open_camera).setOnClickListener(new BottomMenuOnClinkListener(this));
        root.findViewById(R.id.btn_cancel).setOnClickListener(new BottomMenuOnClinkListener(this));
        mCameraDialog.setContentView(root);
        Window dialogWindow = mCameraDialog.getWindow();
        dialogWindow.setGravity(Gravity.BOTTOM);
//        dialogWindow.setWindowAnimations(R.style.dialogstyle); // 添加动画
        WindowManager.LayoutParams lp = dialogWindow.getAttributes(); // 获取对话框当前的参数值
        lp.x = 0; // 新位置X坐标
        lp.y = 0; // 新位置Y坐标
        lp.width = (int) getResources().getDisplayMetrics().widthPixels; // 宽度
        root.measure(0, 0);
        lp.height = root.getMeasuredHeight();

        lp.alpha = 9f; // 透明度
        dialogWindow.setAttributes(lp);
        mCameraDialog.show();
    }

}
