package com.example;

import javax.persistence.*;

public class Entity1 {
    @Entity
    @Table(name = "customers")
    public class Customer {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id")
        private Long id;

        @Column(name = "name")
        private String name;

        @Column(name = "message")
        private String message;

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        /**
         * @return the id
         */
        public Integer getId() {
            return Math.toIntExact(id);
        }

        /**
         * @param id the id to set
         */
        public void setId(Integer id) {
            this.id = Long.valueOf(id);
        }

        /**
         * @return the name
         */
        public String getName() {
            return name;
        }

        /**
         * @param name the name to set
         */
        public void setName(String name) {
            this.name = name;
        }

        /**
         * @return the message
         */
        public String getMessage() {
            return message;
        }

        /**
         * @param message the message to set
         */
        public void setMessage(String message) {
            this.message = message;
        }
    }

}
