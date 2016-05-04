package com.asha.md360player4android;

import java.io.IOException;

import tv.danmaku.ijk.media.player.IMediaPlayer;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;

/**
 * Created by hzqiujiadi on 16/4/5.
 * hzqiujiadi ashqalcn@gmail.com
 */
public class MediaPlayerWrapper implements IjkMediaPlayer.OnPreparedListener {
    protected IjkMediaPlayer mPlayer;
    private IjkMediaPlayer.OnPreparedListener mPreparedListener;

    public void init(){
        mPlayer = new IjkMediaPlayer();
        mPlayer.setOnPreparedListener(this);

        mPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "mediacodec", 1);
        mPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "mediacodec-auto-rotate", 1);
        mPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "overlay-format", IjkMediaPlayer.SDL_FCC_RV32);
        mPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "framedrop", 60);
        mPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "max-fps", 0);
        mPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_CODEC, "skip_loop_filter", 48);
    }

    /*
    protected void openLocalFile(){
        AssetFileDescriptor afd = getResources().openRawResourceFd(R.raw.demo);
        if (afd == null) return;
        try {
            mPlayer.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
            afd.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    */

    protected void openRemoteFile(String url){
        try {
            //"http://vod.moredoo.com/u/7575/m3u8/854x480/25883d97c738b1be48d1e106ede2789c/25883d97c738b1be48d1e106ede2789c.m3u8"
            mPlayer.setDataSource(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public IMediaPlayer getPlayer() {
        return mPlayer;
    }

    public void play() {
        stop();
        if (mPlayer == null) return;
        mPlayer.prepareAsync();
    }

    private void stop(){
        if (mPlayer == null) return;
        if (mPlayer.isPlaying()){
            mPlayer.stop();
        }
    }

    public void onStop() {
        stop();
    }

    public void onDestroy() {
        if (mPlayer != null) mPlayer.release();
        mPlayer = null;
    }

    public void setPreparedListener(IMediaPlayer.OnPreparedListener mPreparedListener) {
        this.mPreparedListener = mPreparedListener;
    }

    @Override
    public void onPrepared(IMediaPlayer mp) {
        mp.start();
        if (mPreparedListener != null) mPreparedListener.onPrepared(mp);
    }
}
