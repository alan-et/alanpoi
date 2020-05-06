package com.alanpoi.etactivity.agent;

import com.alanpoi.etactivity.agent.annotation.IService;
import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.core.type.filter.AnnotationTypeFilter;

import java.util.Set;

/**
 * Bean scanner
 *
 * @author pengzhuoxun
 * @since 1.3.0
 */

public class ActivityClassPathBeanDefinitionScanner extends ClassPathBeanDefinitionScanner {

    public ActivityClassPathBeanDefinitionScanner(BeanDefinitionRegistry registry, boolean useDefaultFilters) {
        super(registry, useDefaultFilters);
    }

    @Override
    protected void registerDefaultFilters() {
        addIncludeFilter(new AnnotationTypeFilter(IService.class));
    }

    @Override
    protected Set<BeanDefinitionHolder> doScan(String... basePackages) {
        Set<BeanDefinitionHolder> beanDefinitionHolders = super.doScan(basePackages);
        GenericBeanDefinition definition;
        for (BeanDefinitionHolder beanDefinitionHolder : beanDefinitionHolders) {
            definition = (GenericBeanDefinition) beanDefinitionHolder.getBeanDefinition();
            try {
                definition.getPropertyValues().add("cls", Class.forName(definition.getBeanClassName()));
            } catch (Exception e) {
                logger.error("", e);
            }
            definition.setBeanClass(ActivityFactoryBean.class);
        }
        return beanDefinitionHolders;
    }

    @Override
    protected boolean isCandidateComponent(AnnotatedBeanDefinition beanDefinition) {
        return beanDefinition.getMetadata().isInterface() && beanDefinition.getMetadata().isIndependent();
    }

    @Override
    protected boolean checkCandidate(String beanName, BeanDefinition beanDefinition) throws IllegalStateException {
        return super.checkCandidate(beanName, beanDefinition);
    }
}
