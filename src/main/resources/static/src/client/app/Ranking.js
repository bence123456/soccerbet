import React from 'react';
import {List, ListItem} from 'material-ui/List';
import Avatar from 'material-ui/Avatar';
import Divider from 'material-ui/Divider';
import MobileTearSheet from './MobileTearSheet';
import {Table, TableBody, TableHeader, TableHeaderColumn, TableRow, TableRowColumn} from 'material-ui/Table';

class Ranking extends React.Component {	

	constructor(props) {
		super(props);
		this.state = {users: []};
	}

	componentDidMount() {
       fetch('http://localhost:8080/api/users/search/findAllByOrderByPointsDesc')
       .then((response) => { return response.json() })
       .then( (json) => {this.setState({users: json._embedded.users}); });
	}

    render() {
        var userNodes = this.state.users.map(function (user, i) {
			var name = user.name;
			var points = user.points;

            return (
                <TableRow key={i} >
                    <TableRowColumn> {i + 1} </TableRowColumn>
                    <TableRowColumn> {name} </TableRowColumn>
                    <TableRowColumn> {points} </TableRowColumn>
                </TableRow>
            );
        });

        return (
			<div style={{display: 'flex', alignItems: 'center', justifyContent: 'center'}}>
			    <Table style={{width: 800}}>
                    <TableHeader displaySelectAll={false} >
                        <TableRow>
                            <TableHeaderColumn> Helyezés </TableHeaderColumn>
                            <TableHeaderColumn> Felhasználónév </TableHeaderColumn>
                            <TableHeaderColumn> Pontszám </TableHeaderColumn>
                        </TableRow>
                    </TableHeader>
                    <TableBody displayRowCheckbox={false}>
				        {userNodes}
				    </TableBody>
                </Table>
			</div>
        );
    }
}

export default Ranking;