package com.eason.majiang.utils;

import com.eason.majiang.object.TileSuit;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class LanguageManager {
    private LanguageManager()
    {}
    private static final ResourceBundle resource = ResourceBundle.getBundle("message");

    @FunctionalInterface
    public static interface Message
    {
        String str();
    }

    private static final String TILE_SUIT_PREFIX = "TILE_SUIT_";
    private static final String TILE_RANK_PREFIX = "TILE_RANK_";
    private static final String ACTION_TYPE_PREFIX = "ACTION_TYPE_";
    private static final String PLAYER_LOCATION_PREFIX = "PLAYER_LOCATION_";
    private static final String PLAYER_RELATION_PREFIX = "PLAYER_RELATION_";
    private static final String FAN_TYPE_PREFIX = "FAN_TYPE_";

    private static final Map<String, Message> messageCache = Collections
            .synchronizedMap(new HashMap<>());

    private static String ofName(String name) {
        return resource.getString(name);
    }

    private static Message message(String name) {
        Message message = messageCache.get(name);
        if (message == null) {
            messageCache.put(name, message = () -> ofName(name));
        }
        return message;
    }

    public static String str(TileSuit suit) {
        return message(suit).str();
    }

    public static Message message(TileSuit suit) {
        return message(TILE_SUIT_PREFIX + suit.name());
    }

    public static enum ExtraMessage implements Message {
        /**
         * 发牌结束的提示
         */
        DEAL_DONE,
        /**
         * 庄家
         */
        ZHUANG,
        /**
         * 听牌
         */
        TING,
        /**
         * 空格键
         */
        SPACE_KEY,
        /**
         * M键
         */
        M_KEY,
        /**
         * H键
         */
        H_KEY,
        /**
         * 斜杠（/）键
         */
        SLASH_KEY,
        /**
         * 逗号和句号键
         */
        COMMA_AND_PERIOD_KEY,
        /**
         * 移动到下一个或上一个选项
         */
        MOVE_CHOICE,
        /**
         * 放弃选择
         */
        PASS,
        /**
         * 自摸
         */
        ZIMO,
        /**
         * 点炮
         */
        DIANPAO,
        /**
         * 番的总计
         */
        FAN_TOTLE,
        /**
         * 番的单位
         */
        FAN,
        /**
         * 窗口太窄的提示
         */
        WINDOW_TOO_NARROW,
        /**
         * 询问是否开始新游戏，Y/N
         */
        NEW_GAME_QUESTION,
        /**
         * 左右移动的按键提示
         */
        MOVE_TIP,
        /**
         * github地址提示
         */
        GITHUB_TIP,
        ;
        @Override
        public String str() {
            return LanguageManager.ofName(name());
        }

    }


}
