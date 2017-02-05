package sk.slinner;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Scanner;

public class Main {

    private static final int MODULO = 1_000_000_007;

    public static void main(String[] args) {
        Scanner scanner = new Scanner( System.in );

        int nTestCases = scanner.nextInt();

        for ( int i = 1; i <= nTestCases; ++i ){

            int nEmployees = scanner.nextInt();
            int nFriendships = scanner.nextInt();

            boolean[] employeeHasFriend = new boolean[ nEmployees + 1 ];
            LinkedList<HashSet<Integer>> fireRoutes = new LinkedList<>();

            friendships: for ( int j = 1; j <= nFriendships; ++j ){

                Integer employee1 = scanner.nextInt();
                Integer employee2 = scanner.nextInt();

                employeeHasFriend [employee1 ] = true;
                employeeHasFriend [employee2 ] = true;

                for ( HashSet<Integer> fireRoute : fireRoutes ) {
                    if ( fireRoute.contains( employee1 ) ) {
                        fireRoute.add( employee2 );
                        continue friendships;
                    } else if ( fireRoute.contains( employee2 ) ) {
                        fireRoute.add( employee1 );
                        continue friendships;
                    }
                }

                fireRoutes.add( new HashSet<>( Arrays.asList( employee1, employee2 ) ) );

            }

            int lonelyEmployees = 0;
            for ( int k = 1; k < employeeHasFriend.length; ++k ){
                if ( ! employeeHasFriend[k] )
                    ++lonelyEmployees;
            }

            int totalFireRoutes = fireRoutes.size() + lonelyEmployees;

            int captains = 0;
            for ( HashSet<Integer> fireRoute : fireRoutes ) {
                if ( captains == 0 ) {
                    captains = fireRoute.size();
                } else {
                    captains = captains * fireRoute.size();
                }
            }

            System.out.println( totalFireRoutes + " " + captains % MODULO );

        }

        scanner.close();
    }
}
