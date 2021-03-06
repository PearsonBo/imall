package com.bo.imall.dao;


import com.bo.imall.model.common.AbstractBo;
import com.bo.imall.model.common.AbstractSo;
import com.bo.imall.model.common.AbstractVo;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: Hu Jianbo
 * @Date: 2018/9/10 0010 下午 23:33
 */
public interface BaseDao<T extends AbstractBo>{

    /**
     * 批量插入 根据<code>List<Long></code>插入 <code>com.best.capital.pay.model.associate.bo.AbstractBo</code>的继承对象
     *
     * @param insertList 待插入集合
     */
    void batchInsert(List<T> insertList);

    /**
     * 插入
     *
     * @param bo 待插入对象
     * @return 插入是否成功
     */
    Long insert(T bo);

    /**
     * 更新 更新 T <code>com.best.capital.pay.model.associate.bo.AbstractBo</code>的继承对象
     *
     * @param bo 待更新对象
     * @return 更新是否成功
     */
    int update(T bo);

    /**
     * 删除 删除 T <code>com.best.capital.pay.model.associate.bo.AbstractBo</code>的继承对象
     *
     * @param id 待删除对象Id
     * @return 删除是否成功
     */
    int delete(Long id);

    /**
     * 根据id集合删除 根据<code>List<Long></code>查找 <code>com.best.capital.pay.model.associate.bo.AbstractBo</code>的继承对象
     *
     * @param idList 待删除对象Id集合
     * @return 是否删除成功
     */
    int deleteByIdList(List<Long> idList);

    /**
     * 查找 根据id查找 T <code>com.best.capital.pay.model.associate.bo.AbstractBo</code>的继承对象
     *
     * @param id 待查找对象Id
     * @return 查找到的结果
     */
    T findBo(Long id);

    /**
     * 查询 根据<code>com.best.capital.pay.model.associate.so.AbstractSo</code>查找 T <code>com.best.capital.pay.model.associate.bo.AbstractBo</code>的继承对象
     *
     * @param so 查询条件
     * @return 查询结果
     */
    List<T> listBoBySo(AbstractSo so);

    /**
     * 查询id 根据<code>com.best.capital.pay.model.associate.so.AbstractSo</code>查找 Long <code>com.best.capital.pay.model.associate.bo.AbstractBo</code>的继承对象
     *
     * @param so 查询条件
     * @return 查询结果
     */
    List<Long> listIdBySo(AbstractSo so);

    /**
     * 查询数量 根据<code>com.best.capital.pay.model.associate.so.AbstractSo</code>得到数量
     *
     * @param so 查询条件
     * @return 查询结果
     */
    int countBySo(AbstractSo so);

    /**
     * 查找 根据id查找<code>com.best.capital.pay.model.associate.vo.AbstractVo</code>的继承对象
     *
     * @param id 待查找对象Id
     * @return 查找结果
     */
    <U extends AbstractVo> U findVo(Long id);

    /**
     * 查询 根据<code>com.best.capital.pay.model.associate.so.AbstractSo</code>查找 <code>com.best.capital.pay.model.associate.vo.AbstractVo</code>的继承对象     *
     *
     * @param so 查询条件
     * @return 查找结果
     */
    T findBoBySo(AbstractSo so);

    /**
     * 查询 根据<code>com.best.capital.pay.model.associate.so.AbstractSo</code>查找 <code>com.best.capital.pay.model.associate.vo.AbstractVo</code>的继承对象
     *
     * @param so 查询条件
     * @return 查询结果
     */
    <U extends AbstractVo> List<U> listVoBySo(AbstractSo so);

    /**
     * 分页查询 根据<code>com.best.capital.pay.model.associate.so.AbstractSo</code>查找分页列表 <code>com.best.capital.pay.model.associate.vo.AbstractVo</code>的继承对象
     *
     * @param so 查询条件
     * @return 查询结果
     */
    <U extends AbstractVo> List<U> listPaginationVoBySo(AbstractSo so);

    /**
     * 根据Id集合查询 根据<code>List<Long></code>查找 <code>com.best.capital.pay.model.associate.bo.AbstractBo</code>的继承对象
     *
     * @param idList 待查询Id集合
     * @return 查询结果
     */
    List<T> listBoByIdList(List<Long> idList);

    /**
     * 根据Id集合查询 根据<code>List<Long></code>查找 <code>com.best.capital.pay.model.associate.vo.AbstractVo</code>的继承对象
     *
     * @param idList 待查询Id集合
     * @return 查询结果
     */
    <U extends AbstractVo> List<U> listVoByIdList(List<Long> idList);

}
