package com.scut.mall.dao;

import com.scut.mall.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

/**
 * @ Author     ：Bin Liu
 * @ Date       ：2018/11/23 0:05
 * @ Description：产品数据访问层接口
 * @ Modified By：
 */
public interface ProductDao extends JpaRepository<Product, Integer> {

    /**
     * create by: Bin Liu
     * description: 通过id获得产品信息
     * create time: 2018/11/23 9:39
     * @Param: null
     * @return 
     */
    Product findProductById(Integer id);
    
    /**
     * create by: Bin Liu
     * description: 通过二级分类查找商品列表
     * create time: 2018/11/23 9:21
     * @param csId
     * @param pageable
     * @return
     */
    List<Product> findByCsId(int csId, Pageable pageable);

    List<Product> findByCsIdIn(List<Integer> csIds, Pageable pageable);

    /**
     * create by: Bin Liu
     * description: 通过标题搜索商品
     * create time: 2018/11/23 9:22
     * @param keyword
     * @return
     */
    List<Product> findByTitleIsLike(String keyword);

    /**
     * create by: Bin Liu
     * description: 查找某个日期之后上架的商品
     * create time: 2018/11/23 9:23
     * @param date
     * @param pageable
     * @return
     */
    List<Product> findByDateAfter(Date date, Pageable pageable);

    /**
     * create by: Bin Liu
     * description: 查找热门商品
     * create time: 2018/11/23 9:23
     * @param isHot
     * @param pageable
     * @return
     */
    List<Product> findByIsHot(int isHot,Pageable pageable);

    /**
     * create by: Bin Liu
     * description: 查询最新商品，最近上新的24个商品
     * create time: 2018/11/23 9:24
     * @param pageable
     * @return
     */
    @Query(value = "SELECT * FROM (SELECT  * FROM product ORDER BY date DESC limit 0,24) a /*#pageable*/",nativeQuery = true)
    List<Product> findNew(Pageable pageable);
}
