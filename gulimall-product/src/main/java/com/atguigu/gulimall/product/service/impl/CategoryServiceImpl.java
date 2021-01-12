package com.atguigu.gulimall.product.service.impl;

import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atguigu.common.utils.PageUtils;
import com.atguigu.common.utils.Query;

import com.atguigu.gulimall.product.dao.CategoryDao;
import com.atguigu.gulimall.product.entity.CategoryEntity;
import com.atguigu.gulimall.product.service.CategoryService;


@Service("categoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryDao, CategoryEntity> implements CategoryService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CategoryEntity> page = this.page(
                new Query<CategoryEntity>().getPage(params),
                new QueryWrapper<CategoryEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<CategoryEntity> listWithTree() {
        //查出所有分类
        List<CategoryEntity> categoryEntities = baseMapper.selectList(null);
        //组装树形结构
        List<CategoryEntity> levelOneMenu = categoryEntities.stream().filter(categoryEntity ->
                categoryEntity.getParentCid() == 0
        ).map(menu->{
            menu.setChildren(getChildrens(menu,categoryEntities));
            return menu;
        }).sorted(Comparator.comparing(CategoryEntity::getSort)).collect(Collectors.toList());


        return levelOneMenu;
    }

    @Override
    public void removeCategoryByIds(List<Long> catIds) {
        //TODO 检查当前删除菜单是否被引用
        baseMapper.deleteBatchIds(catIds);
    }

    /**
     * 递归查询所有菜单子菜单
     * @param root
     * @param all
     * @return
     */
    private List<CategoryEntity> getChildrens(CategoryEntity root,List<CategoryEntity> all){
        List<CategoryEntity> children = all.stream().filter(categoryEntity -> {
            return categoryEntity.getParentCid().equals(root.getCatId());
        }).map(categoryEntity -> {
            categoryEntity.setChildren(getChildrens(categoryEntity,all));
            return categoryEntity;
        }).sorted(Comparator.comparing(CategoryEntity::getSort)).collect(Collectors.toList());
        return children;
    }
}