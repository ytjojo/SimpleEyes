package com.jennifer.andy.simpleeyes.ui.feed.model

import com.jennifer.andy.simpleeyes.entity.Tab
import com.jennifer.andy.simpleeyes.net.Api
import com.jennifer.andy.simpleeyes.rx.RxHelper
import com.jennifer.andy.simpleeyes.ui.base.model.BaseModel
import io.reactivex.Observable


/**
 * Author:  andy.xwt
 * Date:    2017/9/22 13:52
 * Description:
 */

class FeedModel : BaseModel {

    /**
     * 获取发现tab栏
     */
    fun getDiscoveryTab(): Observable<Tab> = Api.getDefault().getDiscoveryTab().compose(RxHelper.handleResult())

    /**
     * 获取全部分类信息
     */
    fun loadAllCategoriesInfo() = Api.getDefault().getAllCategoriesInfo().compose(RxHelper.handleResult())

    /**
     * 获取排行榜tab栏
     */
    fun getRankListTab() = Api.getDefault().getRankListTab().compose(RxHelper.handleResult())

    /**
     * 获取专题信息
     */
    fun getTopicInfo() = Api.getDefault().getTopicInfo().compose(RxHelper.handleResult())

    /**
     * 获取tag信息(360全景信息)，注意是在onNext进行设置数据
     */
    fun getTagInfo(tagId: String, strategy: String) = Api.getDefault().getTagInfo(tagId, strategy).compose(RxHelper.handleResult())

    /**
     * 获取种类下tab信息
     */
    fun getCategoryTagIno(id: String) = Api.getDefault().getCategoryTagInfo(id).compose(RxHelper.handleResult())
}