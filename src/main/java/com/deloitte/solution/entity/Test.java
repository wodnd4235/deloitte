package com.deloitte.solution.entity;

public class Test {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TEST.ID
     *
     * @mbg.generated Fri Sep 16 16:48:13 KST 2022
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TEST.NAME
     *
     * @mbg.generated Fri Sep 16 16:48:13 KST 2022
     */
    private String name;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column TEST.ID
     *
     * @return the value of TEST.ID
     *
     * @mbg.generated Fri Sep 16 16:48:13 KST 2022
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column TEST.ID
     *
     * @param id the value for TEST.ID
     *
     * @mbg.generated Fri Sep 16 16:48:13 KST 2022
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column TEST.NAME
     *
     * @return the value of TEST.NAME
     *
     * @mbg.generated Fri Sep 16 16:48:13 KST 2022
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column TEST.NAME
     *
     * @param name the value for TEST.NAME
     *
     * @mbg.generated Fri Sep 16 16:48:13 KST 2022
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }
}