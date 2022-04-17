public class Sort 
{
    /**
     * 
     * @param strs
     */
    public static void stringSortLH(String[] strs)
    {
        String temp;
        for (int j = 0; j < strs.length; j++) 
        {
            for (int i = j + 1; i < strs.length; i++) 
            {
                if (strs[i].compareTo(strs[j]) < 0) 
                {
                    temp = strs[j];
                    strs[j] = strs[i];
                    strs[i] = temp;
                }
            }    
        }
    }

    public static void stringSortLH(Professor[] professors)
    {
        Professor temp;
        for (int j = 0; j < professors.length; j++) 
        {
            for (int i = j + 1; i < professors.length; i++) 
            {
                if (professors[i].getName().compareTo(professors[j].getName()) < 0) 
                {
                    temp = professors[j];
                    professors[j] = professors[i];
                    professors[i] = temp;
                }
            }    
        }
    }
    
    public static void stringSortLH(Student[] students)
    {
        Student temp;
        for (int j = 0; j < students.length; j++) 
        {
            for (int i = j + 1; i < students.length; i++) 
            {
                if (students[i].getName().compareTo(students[j].getName()) < 0) 
                {
                    temp = students[j];
                    students[j] = students[i];
                    students[i] = temp;
                }
            }    
        }
    }

    
}
