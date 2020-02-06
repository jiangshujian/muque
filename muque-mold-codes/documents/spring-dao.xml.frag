<bean id="${tableBean}Dao" class="${packageName}.dao.${tableBeanClass}Dao">
	<property name="dataSource" ref="${databaseName}DataSource" />
</bean>
