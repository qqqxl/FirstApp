package com.example.firstapp.utils;

import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.text.Editable;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;

import com.example.firstapp.R;

public class keyBoardUtils {
    private final Keyboard k1;//自定义键盘
    private KeyboardView keyboardView;
    private EditText editText;
    public interface onEnsureListener{
        public void onEnsure();
    }
    onEnsureListener onEnsureListener;

    public void setOnEnsureListener(keyBoardUtils.onEnsureListener onEnsureListener) {
        this.onEnsureListener = onEnsureListener;
    }

    public keyBoardUtils(KeyboardView keyboardView, EditText editText) {
        this.keyboardView = keyboardView;
        this.editText = editText;
        this.editText.setInputType(InputType.TYPE_NULL);//取消弹出系统键盘
        k1 =new Keyboard(this.editText.getContext(), R.xml.keyborad);

        this.keyboardView.setKeyboard(k1);//设置要显示键盘的样式
        this.keyboardView.setEnabled(true);
        this.keyboardView.setPreviewEnabled(false);
        this.keyboardView.setOnKeyboardActionListener(listener);//设置键盘按钮被点击了的监听

    }

    KeyboardView.OnKeyboardActionListener listener = new KeyboardView.OnKeyboardActionListener() {
        @Override
        public void onPress(int primaryCode) {
        }

        @Override
        public void onRelease(int primaryCode) {
        }

        @Override
        public void onKey(int primaryCode, int[] keyCodes) {
            Editable editable=editText.getText();
            int start=editText.getSelectionStart();
            switch (primaryCode){
                case Keyboard.KEYCODE_DELETE://点击删除
                   if (editable!=null&&editable.length()>0){
                       if(start>0){
                           editable.delete(start-1,start);
                       }
                   }
                    break;
                    case Keyboard.KEYCODE_CANCEL://点击清零
                        editable.clear();
                        break;
                        case Keyboard.KEYCODE_DONE://点击完成
                            onEnsureListener.onEnsure();
                            //通过接口回调的方法，当点击确定是，可以调用这个方法
                            break;
                            default://其他数字插入
                            editable.insert(start,Character.toString((char) primaryCode));
                    break;
            }
        }
        @Override
        public void onText(CharSequence text) {
        }
        @Override
        public void swipeLeft() {
        }
        @Override
        public void swipeRight() {
        }
        @Override
        public void swipeDown() {
        }
        @Override
        public void swipeUp() {
        }
    };
  //显示键盘方法
 public void  setKeyboardView() {
     int visibility = keyboardView.getVisibility();
     if (visibility == View.VISIBLE || visibility == View.INVISIBLE) {
         keyboardView.setVisibility(View.VISIBLE);
     }
 }

//隐藏键盘
    public void  hideKeyboard(){
     int visibility = keyboardView.getVisibility();
     if (visibility==View.VISIBLE||visibility==View.INVISIBLE){
         keyboardView.setVisibility(View.GONE);
     }
    }

//     public void hideKeyboard(){
//         int visibility=keyboardView.getVisibility();
//         if(visibility== View.VISIBLE||visibility==View.INVISIBLE){
//             keyboardView.setVisibility(View.GONE);}


}