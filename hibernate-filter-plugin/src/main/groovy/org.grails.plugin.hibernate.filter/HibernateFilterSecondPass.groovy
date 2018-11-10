package org.grails.plugin.hibernate.filter

import org.grails.datastore.mapping.model.PersistentEntity
import org.grails.orm.hibernate.cfg.HibernateMappingContext
import org.hibernate.MappingException
import org.hibernate.boot.spi.InFlightMetadataCollector
import org.hibernate.cfg.SecondPass
import org.hibernate.mapping.PersistentClass

class HibernateFilterSecondPass implements SecondPass {

    HibernateMappingContext mappingContext
    protected InFlightMetadataCollector mappings

    HibernateFilterSecondPass(InFlightMetadataCollector mappings, HibernateMappingContext mappingContext) {
        this.mappings = mappings
        this.mappingContext = mappingContext
    }

	void doSecondPass(Map persistentClasses) throws MappingException {
		mappingContext.persistentEntities.each { PersistentEntity persistentEntity ->
			if (persistentEntity.hasProperty('hibernateFilters', Closure)) {
				new HibernateFilterBuilder(
					mappings,
					persistentEntity,
					(PersistentClass)persistentClasses.get(persistentEntity.name)
				)
			}
		}
	}
}
