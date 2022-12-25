package com.myCompany.RepairAgency.model.db.PostgeSQL.repository;

import com.myCompany.RepairAgency.model.db.PostgeSQL.ConnectionPool;
import com.myCompany.RepairAgency.model.db.PostgeSQL.Query;
import com.myCompany.RepairAgency.model.db.PostgeSQL.QueryExecutioner;
import com.myCompany.RepairAgency.model.db.PostgeSQL.factory.RoleFactory;
import com.myCompany.RepairAgency.model.db.abstractDB.abstractRepository.entity.iRoleRepository;
import com.myCompany.RepairAgency.model.entity.Role;

import java.sql.Connection;
import java.util.ArrayList;

public class RoleRepository implements iRoleRepository {
    @Override
    public Role getById(long id) {
        Connection conn = ConnectionPool.getConnection();
        Role res = QueryExecutioner.readEntity(RoleFactory.ins, conn, Query.RolesQuery.SELECT_BY_ID, id);
        ConnectionPool.releaseConnection(conn);
        return res;
    }

    @Override
    public ArrayList<Role> getAll() {
        Connection conn = ConnectionPool.getConnection();
        ArrayList<Role> res = QueryExecutioner.readList(RoleFactory.ins, conn, Query.RolesQuery.SELECT_ALL);
        ConnectionPool.releaseConnection(conn);
        return res;
    }

    @Override
    public ArrayList<Role> getWithPagination(int skip, int quantity) {
        Connection conn = ConnectionPool.getConnection();
        ArrayList<Role> res = QueryExecutioner.readList(RoleFactory.ins, conn, Query.RolesQuery.SELECT_WITH_PAGINATION, skip, quantity);
        ConnectionPool.releaseConnection(conn);
        return res;
    }
}
