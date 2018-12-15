package xin.lrvik.plantsvszombies;

import android.view.MotionEvent;
import android.widget.Toast;

import org.cocos2d.actions.instant.CCCallFunc;
import org.cocos2d.actions.interval.CCDelayTime;
import org.cocos2d.actions.interval.CCMoveTo;
import org.cocos2d.actions.interval.CCSequence;
import org.cocos2d.layers.CCLayer;
import org.cocos2d.layers.CCScene;
import org.cocos2d.nodes.CCDirector;
import org.cocos2d.nodes.CCLabel;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.transitions.CCFadeTransition;
import org.cocos2d.transitions.CCJumpZoomTransition;
import org.cocos2d.types.CGRect;
import org.cocos2d.types.CGSize;
import org.cocos2d.types.ccColor3B;

/**
 * Author by 豢涵, Email huanhanfu@126.com, Date on 2018/12/15.
 */
public class ShopLayer extends CCLayer {

    private final CCSprite ccSprite_store_p;
    private final CCSprite ccSprite_store_n;
    private final CCSprite ccSprite_lawnmower;
    private final CCLabel ccLabel_tip;

    public ShopLayer() {
        CGSize winSize = CCDirector.sharedDirector().getWinSize();
        CCSprite ccSprite_store_car = CCSprite.sprite("other/store_car.jpg");
        ccSprite_store_car.setAnchorPoint(0, 0);
        ccSprite_store_car.setScale(1.6f);
        ccSprite_store_car.setPosition(winSize.getWidth() / 2 - ccSprite_store_car.getBoundingBox().size.getWidth() / 2, 0);
        addChild(ccSprite_store_car);

        ccSprite_store_n = CCSprite.sprite("other/Store_N.png");
        ccSprite_store_n.setScale(1.6f);
        ccSprite_store_n.setAnchorPoint(0, 0);
        ccSprite_store_n.setPosition(800, 185);
        addChild(ccSprite_store_n);


        ccSprite_store_p = CCSprite.sprite("other/Store_P.png");
        ccSprite_store_p.setScale(1.6f);
        ccSprite_store_p.setAnchorPoint(0, 0);
        ccSprite_store_p.setPosition(240, 195);
        addChild(ccSprite_store_p);

        CCLabel ccLabel = CCLabel.makeLabel("返回", "", 40);
        ccLabel.setColor(ccColor3B.ccGREEN);
        ccLabel.setPosition(ccSprite_store_p.getPosition().x + 70, ccSprite_store_p.getPosition().y + 70);
        addChild(ccLabel);

        ccSprite_lawnmower = CCSprite.sprite("other/lawnmower.png");
        ccSprite_lawnmower.setScale(2f);
        ccSprite_lawnmower.setAnchorPoint(0, 0);
        ccSprite_lawnmower.setPosition(500, 350);
        addChild(ccSprite_lawnmower);

        ccLabel_tip = CCLabel.makeLabel("购买成功", "", 40);
        ccLabel_tip.setColor(ccColor3B.ccRED);
        ccLabel_tip.setPosition(winSize.getWidth() / 2, winSize.getHeight()-80);
        addChild(ccLabel_tip);
        ccLabel_tip.setVisible(false);

        setIsTouchEnabled(true);
    }


    @Override
    public boolean ccTouchesBegan(MotionEvent event) {
        if (CGRect.containsPoint(ccSprite_lawnmower.getBoundingBox(), convertTouchToNodeSpace(event))) {
            // todo 点击了除草机
            showText("购买成功");
        } else if (CGRect.containsPoint(ccSprite_store_p.getBoundingBox(), convertTouchToNodeSpace(event))) {
            // todo 点击了返回
            CCScene ccScene = CCScene.node();
            ccScene.addChild(new MenuLayer());
            CCFadeTransition ccFadeTransition = CCFadeTransition.transition(2, ccScene);
            CCDirector.sharedDirector().runWithScene(ccFadeTransition);
        }

        return super.ccTouchesBegan(event);
    }

    private void showText(String text) {
        ccLabel_tip.setVisible(true);
        ccLabel_tip.setString(text);

        CCDelayTime ccDelayTime = CCDelayTime.action(1);
        CCCallFunc ccCallFunc = CCCallFunc.action(this, "hide");
        CCSequence ccSequence = CCSequence.actions(ccDelayTime, ccCallFunc);
        runAction(ccSequence);
    }

    public void hide() {
        ccLabel_tip.setVisible(false);
    }
}
