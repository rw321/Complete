package com.example.complete.fragment;

import android.os.Bundle;

import com.example.complete.R;
import com.example.complete.base.BaseFragment;
import com.example.complete.databinding.FragmentThirdBinding;

public class ThirdFragment extends BaseFragment<FragmentThirdBinding> {
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_third;
    }

    @Override
    protected void initData() {
        super.initData();
        mContentBinding.expandText.initWidth(getActivity().getWindowManager().getDefaultDisplay().getWidth());
        mContentBinding.expandText.setMaxLines(4);
        mContentBinding.expandText.setCloseText("她像个顺从的孩子般端坐在牢房角落的小凳子上，仿如一件古怪的战利品。脚边的稻草上搁着白镴盘子，里面是吃剩的食物。我留意到叔叔送来不少肉，甚至还有他自己吃的那种白面包；可是她没动几口。我发现自己一直盯着她看，打量她脚上那双男孩才穿的马靴，还有那剪短的棕发上扣着的男式软帽，就好像她是什么抓来供我们消遣的奇珍异兽" +
                        "，像只从山遥水远的埃塞俄比亚抓来供卢森堡贵族取乐的小狮子，我们新添的一件收藏品而已。背后的夫人画了个十字，悄声道：“她是女巫吗？" +
                "我不知道。谁又能知道呢？ 摘录来自: 菲利帕·格里高利. “金雀花与都铎系列（套装7册）【女性历史小说新时代！“英国宫廷小说女王”重述玫瑰战争、都铎王朝百年宫闱秘史！《另一个波琳家的女孩》《白王后》《白公主》《西班牙王妃》等历史爱情剧作原著系列作品】。” Apple Books. ");
    }

    public static ThirdFragment getInstance() {
        ThirdFragment fragment = new ThirdFragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }

}
