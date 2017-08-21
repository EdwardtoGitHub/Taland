package com.edward.navigation.UI.View;


import android.widget.Button;

import com.kyleduo.switchbutton.SwitchButton;
import com.lqr.optionitemview.OptionItemView;
import com.lqr.recyclerview.LQRRecyclerView;

public interface ISessionInfoAtView {
    LQRRecyclerView getRvMember();

    OptionItemView getOivGroupName();

    OptionItemView getOivNickNameInGroup();

    SwitchButton getSbToTop();

    Button getBtnQuit();

}
