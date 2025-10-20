package com.maiboroda.dal;

public class RepositoryFactory {
    public static IStudentRepo createFileRepository() {
        return new StudentFileRepo();
    }

    public static IStudentRepo createSerializationRepository() {
        return new StudentSerializationRepository();
    }
}
