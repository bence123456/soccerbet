import React from 'react';
import { Table, TableBody, TableHeader, TableHeaderColumn, TableRow, TableRowColumn} from 'material-ui/Table';

const tableStyle = {
    tableLayout: 'auto',
    width: '900px',
    marginLeft: 'auto',
    marginRight: 'auto',
    background:'transparent'
};

const matchColumnStyle = {
    width: '300px'
}

class MyBets extends React.Component {

	constructor(props) {
		super(props);
		this.state = {
		    bets: []
		};
	}

	componentDidMount() {
        fetch(window.backendHost + '/bet/list?userId=' + window.account.id).then((response) => {
            return response.json() }).then( (json) => { this.setState({bets: json});
        });
	}

    render() {
        var betNodes = this.state.bets.map(function (bet, i) {
            var match = bet.match;

            return (
                <TableRow key={i} style={{color: 'black'}} >
                    <TableRowColumn> {i + 1} </TableRowColumn>
                    <TableRowColumn> {match.homeTeamName} - {match.awayTeamName} </TableRowColumn>
                    <TableRowColumn> {match.round} </TableRowColumn>
                    <TableRowColumn> {bet.homeTeamGoals} - {bet.awayTeamGoals} </TableRowColumn>
                    <TableRowColumn> {match.homeTeamGoals} - {match.awayTeamGoals} </TableRowColumn>
                    <TableRowColumn> {bet.gainedPoints} </TableRowColumn>
                </TableRow>
            );
        });

        return (
			<div style={{marginTop: '50px'}} >
			    <Table transparent style={tableStyle} fixedHeader={false}>
                    <TableHeader displaySelectAll={false} adjustForCheckbox={false} style={tableStyle} >
                        <TableRow>
                            <TableHeaderColumn> # </TableHeaderColumn>
                            <TableHeaderColumn> Meccs </TableHeaderColumn>
                            <TableHeaderColumn> Forduló </TableHeaderColumn>
                            <TableHeaderColumn> Tipp </TableHeaderColumn>
                            <TableHeaderColumn> Eredmény </TableHeaderColumn>
                            <TableHeaderColumn> Pont </TableHeaderColumn>
                        </TableRow>
                    </TableHeader>
                    <TableBody displayRowCheckbox={false}>
                        {betNodes}
				    </TableBody>
                </Table>
			</div>
        );
    }
}

export default MyBets;