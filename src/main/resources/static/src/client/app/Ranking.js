import React from 'react';
import { Table, TableBody, TableHeader, TableHeaderColumn, TableRow, TableRowColumn} from 'material-ui/Table';

const tableStyle = {
    width: '800px',
    marginLeft: 'auto',
    marginRight: 'auto',
    background:'transparent'
};

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
                <TableRow key={i} style={{color: 'white'}} >
                    <TableRowColumn> {i + 1} </TableRowColumn>
                    <TableRowColumn> {name} </TableRowColumn>
                    <TableRowColumn> {points} </TableRowColumn>
                </TableRow>
            );
        });

        return (
			<div style={{marginTop: '50px'}} >
			    <Table transparent style={tableStyle}>
                    <TableHeader displaySelectAll={false} adjustForCheckbox={false} >
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